package com.beecommerce.mapper;

import com.beecommerce.dto.reponse.CollectionRequest;
import com.beecommerce.dto.reponse.CollectionResponse;
import com.beecommerce.models.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CollectionMapper {
    CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);

    Collection convertToEntity(CollectionRequest request);

    CollectionResponse convertToResponse(Collection collection);
}
