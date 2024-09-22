package com.beecommerce.mapper;

import com.beecommerce.dto.reponse.CategoryResponse;
import com.beecommerce.dto.request.CategoryRequest;
import com.beecommerce.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", expression = "java(new java.util.Date())")
    @Mapping(target = "parentId", ignore = true)
    Category convertToEntity(CategoryRequest request);
    CategoryResponse convertToResponse(Category category);
}
