package com.beecommerce.mapper;

import com.beecommerce.dto.ShapeResponse;
import com.beecommerce.dto.request.*;
import com.beecommerce.dto.response.CategoryResponse;
import com.beecommerce.dto.response.ProductVariantResponse;
import com.beecommerce.dto.response.ProductResponse;
import com.beecommerce.dto.response.SpecificationResponse;
import com.beecommerce.models.*;
import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.models.enums.SpecificationOption;
import com.beecommerce.repositories.CategoryRepository;
import com.beecommerce.services.S3Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    @Mapping(target = "primaryImage", expression = "java(productRequest.getPrimaryImage() == null || productRequest.getPrimaryImage().isEmpty() ? null : uploadPrimaryImageToS3(productRequest.getPrimaryImage()))")
    @Mapping(target = "images", expression = "java(productRequest.getImages() == null || productRequest.getImages().isEmpty() ? null : uploadImagesToS3(productRequest.getImages()))")
    @Mapping(target = "createdTime", expression = "java(new java.util.Date())")
    @Mapping(target = "avgRating", ignore = true)
    @Mapping(target = "reviewCount", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", expression = "java(mapCategoryIdsToCategories(productRequest.getCategories()))")
    @Mapping(target = "specifications", expression = "java(mapSpecifications(productRequest.getSpecifications()))")
    @Mapping(target = "attributes", expression = "java(mapAttributes(productRequest.getAttributes()))")
    public abstract Product toProduct(ProductRequest productRequest);

    @Mapping(target = "categories", expression = "java(mapToCategoryResponsies(product.getCategories()))")
    @Mapping(target = "specifications", expression = "java(convertSpecificationsToMap(product.getSpecifications()))")
    @Mapping(target = "attributes", expression = "java(convertAttributesToDisplayNames(product.getAttributes()))")
    public abstract ProductResponse toProductResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "images", expression = "java(variantRequest.getImages() == null || variantRequest.getImages().isEmpty() ? null : uploadImagesToS3(variantRequest.getImages()))")
    @Mapping(target = "attributes",  expression = "java(mapAttributesProductVariant(variantRequest.getAttributes()))")
    public abstract ProductVariant toProductVariant(ProductVariantRequest variantRequest);

    @Mapping(target = "attributes", expression = "java(convertAttributesProductVariantToDisplayNames(productVariant.getAttributes()))")
    public abstract ProductVariantResponse toProductVariantResponse(ProductVariant productVariant);

    public abstract ShapeResponse toShapeResponse(ProductShape shape);


    public abstract ProductShape toProductShape(ShapeRequest shapeRequest);

    protected String uploadPrimaryImageToS3(MultipartFile file) {
        return s3Service.uploadFileToS3(file);
    }

    protected List<String> uploadImagesToS3(List<MultipartFile> files) {
        return files.stream()
                .map(file -> s3Service.uploadFileToS3(file))
                .collect(Collectors.toList());
    }

    protected List<Category> mapCategoryIdsToCategories(List<String> categoryIds) {
        return categoryIds.stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + id)))
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
