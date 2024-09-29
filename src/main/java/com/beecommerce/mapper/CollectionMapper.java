package com.beecommerce.mapper;

import com.beecommerce.dto.reponse.CollectionRequest;
import com.beecommerce.dto.response.CollectionResponse;
import com.beecommerce.models.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CollectionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shop", ignore = true)
    @Mapping(target = "isFeatured", ignore = true)
    @Mapping(target = "isPaidForHomeLanding", ignore = true)
    public abstract Collection convertToEntity(CollectionRequest request);

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "shopId", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "isFeatured", ignore = true)
    @Mapping(target = "isPaidForHomeLanding", ignore = true)
    public abstract CollectionResponse convertToResponse(Collection collection);

}
