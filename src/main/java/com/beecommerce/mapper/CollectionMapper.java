package com.beecommerce.mapper;

import com.beecommerce.dto.reponse.CollectionRequest;
import com.beecommerce.dto.response.CollectionResponse;
import com.beecommerce.models.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CollectionMapper {
    CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shop", ignore = true)
    @Mapping(target = "isFeatured", ignore = true)
    @Mapping(target = "isPaidForHomeLanding", ignore = true)
    Collection convertToEntity(CollectionRequest request);


//    Unmapped target properties: "brand, shopId, products, isFeatured, isPaidForHomeLanding".
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "shopId", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "isFeatured", ignore = true)
    @Mapping(target = "isPaidForHomeLanding", ignore = true)
    CollectionResponse convertToResponse(Collection collection);

}
