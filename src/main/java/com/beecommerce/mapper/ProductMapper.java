package com.beecommerce.mapper;

import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "reviewCount", ignore = true)
    @Mapping(target = "avgRating", ignore = true)
    Product convertToEntity(ProductRequest request);

    ProductRequest convertToResponse(Product product);
}
