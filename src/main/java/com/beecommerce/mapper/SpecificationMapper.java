package com.beecommerce.mapper;

import com.beecommerce.dto.request.SpecificationRequest;
import com.beecommerce.dto.response.SpecificationResponse;
import com.beecommerce.models.Specification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import software.amazon.awssdk.services.location.model.GetMapSpritesRequest;

@Mapper
public interface SpecificationMapper {
    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    SpecificationResponse convertToResponse(Specification specification);

    Specification convertToEntity(SpecificationRequest specificationRequest);

}
