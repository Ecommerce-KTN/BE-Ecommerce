package com.beecommerce.mapper;

import com.beecommerce.dto.ShapeResponse;
import com.beecommerce.dto.request.*;
import com.beecommerce.dto.response.CategoryResponse;
import com.beecommerce.dto.response.ProductVariantResponse;
import com.beecommerce.dto.response.ProductResponse;
import com.beecommerce.dto.response.SpecificationResponse;
import com.beecommerce.models.*;
import com.beecommerce.repositories.CategoryRepository;
import com.beecommerce.repositories.SpecificationRepository;
import com.beecommerce.services.S3Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected S3Service s3Service;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected SpecificationRepository specificationRepository;

    @Mapping(target = "primaryImage", expression = "java(productRequest.getPrimaryImage() == null || productRequest.getPrimaryImage().isEmpty() ? null :uploadPrimaryImageToS3(productRequest.getPrimaryImage()))")
    @Mapping(target = "images", expression = "java(productRequest.getImages() == null || productRequest.getImages().isEmpty() ? null :uploadImagesToS3(productRequest.getImages()))")
    @Mapping(target = "createdTime", expression = "java(new java.util.Date())")
    @Mapping(target = "avgRating", ignore = true)
    @Mapping(target = "reviewCount", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", expression = "java(mapCategoryIdsToCategories(productRequest.getCategories()))")
    public abstract Product toProduct(ProductRequest productRequest);

    @Mapping(target = "categories", expression = "java(mapCategoriesToCategoryResponses(product.getCategories()))")
    public abstract ProductResponse toProductResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "images", expression = "java(variantRequest.getImages() == null || variantRequest.getImages().isEmpty() ? null :uploadImagesToS3(variantRequest.getImages()))")
    public abstract ProductVariant toProductVariant(ProductVariantRequest variantRequest);

    public abstract ProductVariantResponse toProductVariantResponse(ProductVariant productVariant);

    @Mapping(target = "options", ignore = true)
    @Mapping(target = "specifications", ignore = true)
    public abstract CategoryResponse toCategoryResponse(Category category);

    @Mapping(target = "id", ignore = true)
    public abstract Category toCategory(CategoryRequest categoryRequest);

    @Mapping(target = "id", ignore = true)
    public abstract SpecificationResponse toSpecificationResponse(Specification specification);

    public abstract Specification toSpecification(SpecificationRequest specificationRequest);

    public abstract ShapeResponse toShapeResponse(ProductShape shape);

    @Mapping(target = "id", ignore = true)
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

    protected List<CategoryResponse> mapCategoriesToCategoryResponses(List<Category> categories) {
        return categories.stream()
                .map(this::toCategoryResponse)
                .collect(Collectors.toList());
    }

    protected List<Specification> mapSpecificationIdsToSpecifications(List<String> specificationIds) {
        return specificationIds.stream()
                .map(id -> specificationRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Specification not found with id: " + id)))
                .collect(Collectors.toList());
    }

    protected List<SpecificationResponse> mapSpecificationsToSpecificationResponses(List<Specification> specifications) {
        return specifications.stream()
                .map(this::toSpecificationResponse)
                .collect(Collectors.toList());
    }
}