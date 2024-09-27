package com.beecommerce.mapper;

import com.beecommerce.dto.response.CategoryResponse;
import com.beecommerce.dto.request.CategoryRequest;
import com.beecommerce.models.Category;
import com.beecommerce.services.S3Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface CategoryMapper {
    S3Service s3Service = new S3Service();
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", expression = "java(new java.util.Date())")
    @Mapping(target = "image", expression = "java(uploadPrimaryImageToS3(request.getImage()))")
    Category convertToEntity(CategoryRequest request);
    CategoryResponse convertToResponse(Category category);

    default String uploadPrimaryImageToS3(MultipartFile file) {
        return s3Service.uploadFileToS3(file);
    }
}
