package com.beecommerce.mapper;

import com.beecommerce.dto.ShapeResponse;
import com.beecommerce.dto.request.*;
import com.beecommerce.dto.response.*;
import com.beecommerce.models.*;
import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.models.enums.SpecificationOption;
import com.beecommerce.repositories.CategoryRepository;
import com.beecommerce.repositories.CollectionRepository;
import com.beecommerce.services.S3Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected S3Service s3Service;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected CategoryMapper categoryMapper;

    @Autowired
    protected CollectionMapper collectionMapper;

    @Autowired
    protected CollectionRepository collectionRepository;

    @Mapping(target = "primaryImage", expression = "java(productRequest.getPrimaryImage() == null || productRequest.getPrimaryImage().isEmpty() ? null : uploadPrimaryImageToS3(productRequest.getPrimaryImage()))")
    @Mapping(target = "images", ignore = true, expression = "java(productRequest.getImages() == null || productRequest.getImages().isEmpty() ? null : uploadImagesToS3(productRequest.getImages()))")
    @Mapping(target = "createdTime", expression = "java(new java.util.Date())")
    @Mapping(target = "avgRating", ignore = true)
    @Mapping(target = "reviewCount", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", expression = "java(mapCategoryIdsToCategories(productRequest.getCategories()))")
    @Mapping(target = "specifications", expression = "java(mapSpecifications(productRequest.getSpecifications()))")
    @Mapping(target = "attributes", ignore = true, expression = "java(mapAttributes(productRequest.getAttributes()))")
    @Mapping(target = "collections", expression = "java(mapCollectionIdsToCollections(productRequest.getCollections()))")
    public abstract Product toProduct(ProductRequest productRequest);

    @Mapping(target = "categories", expression = "java(mapToCategoryResponsies(product.getCategories()))")
    @Mapping(target = "specifications", expression = "java(convertSpecificationsToMap(product.getSpecifications()))")
    @Mapping(target = "attributes", expression = "java(convertAttributesToDisplayNames(product.getAttributes()))")
    @Mapping(target = "priceRange", expression = "java(product.calculatePriceRanges())")
    @Mapping(target = "discountPriceRange", expression = "java(product.calculateDiscountPriceRanges())")
    @Mapping(target = "collections",ignore = true, expression = "java(mapToCollectionResponsies(product.getCollections()))")
    public abstract ProductResponse toProductResponse(Product product);

    @Mapping(target = "images", expression = "java(variantRequest.getImages() == null || variantRequest.getImages().isEmpty() ? null : uploadImagesToS3(variantRequest.getImages()))")
    @Mapping(target = "attributes",  expression = "java(mapAttributesProductVariant(variantRequest.getAttributes()))")
    @Mapping(target = "sold", ignore = true, expression = "java(0L)")
    public abstract ProductVariant toProductVariant(ProductVariantRequest variantRequest);

    @Mapping(target = "attributes", expression = "java(convertAttributesProductVariantToDisplayNames(productVariant.getAttributes()))")
    public abstract ProductVariantResponse toProductVariantResponse(ProductVariant productVariant);

    public abstract ShapeResponse toShapeResponse(ProductShape shape);


    public abstract ProductShape toProductShape(ShapeRequest shapeRequest);

    @Named("uploadPrimaryImage")
    protected String uploadPrimaryImageToS3(MultipartFile file) {
        return file != null && !file.isEmpty() ? s3Service.uploadFileToS3(file) : null;
    }
    @Named("uploadImages")
    protected List<String> uploadImagesToS3(List<MultipartFile> files) {
        return files != null && !files.isEmpty()
                ? files.stream()
                .map(file -> s3Service.uploadFileToS3(file))
                .collect(Collectors.toList())
                : null;
    }

    protected List<Category> mapCategoryIdsToCategories(List<String> categoryIds) {
        return categoryIds.stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + id)))
                .collect(Collectors.toList());
    }

    protected List<Collection> mapCollectionIdsToCollections(List<String> collectionIds) {
        if(collectionIds == null) return Collections.emptyList();
        return collectionIds.stream()
                .map(id -> collectionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Collection not found with id: " + id)))
                .collect(Collectors.toList());
    }

    protected List<CollectionResponse> mapToCollectionResponsies(List<Collection> collections) {
        return collections.stream()
                .map(collectionMapper::convertToResponse)
                .collect(Collectors.toList());
    }

    protected List<CategoryResponse> mapToCategoryResponsies(List<Category> categories) {
        return categories.stream()
                .map(categoryMapper::convertToResponse)
                .collect(Collectors.toList());
    }


    protected Map<SpecificationOption, String> mapSpecifications(Map<String, String> specifications) {
        if (specifications == null) {
            return null;
        }
        return specifications.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> {
                            try {
                                return SpecificationOption.fromDisplayName(entry.getKey());
                            } catch (IllegalArgumentException e) {
                                throw new RuntimeException("Invalid specification option: " + entry.getKey(), e);
                            }
                        },
                        Map.Entry::getValue
                ));
    }

    protected Map<ProductOption, List<String>> mapAttributes(Map<String, List<String>> attributes) {
        if (attributes == null) {
            return null;
        }
        return attributes.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> ProductOption.fromDisplayName(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    protected Map<String, String> convertSpecificationsToMap(Map<SpecificationOption, String> specifications) {
        if (specifications == null) return null;
        return specifications.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getDisplayName(),
                        Map.Entry::getValue
                ));
    }

    protected Map<ProductOption, String> mapAttributesProductVariant(Map<String, String> attributes) {
        if (attributes == null) {
            return null;
        }
        return attributes.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> ProductOption.fromDisplayName(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    protected Map<String, List<String>> convertAttributesToDisplayNames(Map<ProductOption, List<String>> attributes) {
        if (attributes == null) return null;
        return attributes.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getDisplayName(),
                        Map.Entry::getValue
                ));
    }


    protected Map<String, String> convertAttributesProductVariantToDisplayNames(Map<ProductOption, String> attributes) {
        if (attributes == null) return null;
        return attributes.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getDisplayName(),
                        Map.Entry::getValue
                ));
    }

    protected List<String> convertSpecificationsToDisplayNames(Map<SpecificationOption, String> specifications) {
        if (specifications == null) return null;
        return specifications.entrySet().stream()
                .map(entry -> entry.getKey().getDisplayName() + ": " + entry.getValue())
                .collect(Collectors.toList());
    }

    protected List<String> convertProductOptionsToDisplayNames(List<ProductOption> options) {
        if (options == null) return null;
        return options.stream().map(ProductOption::getDisplayName).collect(Collectors.toList());
    }

    protected List<String> convertSpecificationOptionsToDisplayNames(List<SpecificationOption> specifications) {
        if (specifications == null) return null;
        return specifications.stream().map(SpecificationOption::getDisplayName).collect(Collectors.toList());
    }
}
