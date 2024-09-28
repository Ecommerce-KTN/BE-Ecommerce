package com.beecommerce.mapper;

import com.beecommerce.dto.response.CategoryResponse;
import com.beecommerce.dto.request.CategoryRequest;
import com.beecommerce.models.Category;
import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.models.enums.SpecificationOption;
import com.beecommerce.services.S3Service;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", expression = "java(new java.util.Date())")
    @Mapping(target = "image", expression = "java(uploadPrimaryImageToS3(request.getImage()))")
    @Mapping(target = "options", ignore = true)
    @Mapping(target = "specifications", ignore = true)
    public abstract Category convertToEntity(CategoryRequest request);

    @AfterMapping
    protected void convertOptionsAndSpecifications(CategoryRequest request, @MappingTarget Category category) {
        if (request.getOptions() != null) {
            category.setOptions(request.getOptions().stream()
                    .map(ProductOption::valueOf)
                    .collect(Collectors.toList()));
        }
        if (request.getSpecifications() != null) {
            category.setSpecifications(request.getSpecifications().stream()
                    .map(SpecificationOption::valueOf)
                    .collect(Collectors.toList()));
        }
    }

    @Mapping(target = "options", expression = "java(convertProductOptionsToDisplayNames(category.getOptions()))")
    @Mapping(target = "specifications", expression = "java(convertSpecificationOptionsToDisplayNames(category.getSpecifications()))")
    public abstract CategoryResponse convertToResponse(Category category);

    protected List<String> convertProductOptionsToDisplayNames(List<ProductOption> options) {
        if (options == null) return null;
        return options.stream().map(ProductOption::getDisplayName).collect(Collectors.toList());
    }

    protected List<String> convertSpecificationOptionsToDisplayNames(List<SpecificationOption> specifications) {
        if (specifications == null) return null;
        return specifications.stream().map(SpecificationOption::getDisplayName).collect(Collectors.toList());
    }

    protected String uploadPrimaryImageToS3(MultipartFile file) {
        S3Service s3Service = new S3Service();
        return s3Service.uploadFileToS3(file);
    }
}