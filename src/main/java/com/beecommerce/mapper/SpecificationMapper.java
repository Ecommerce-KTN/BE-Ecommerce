package com.beecommerce.mapper;

import com.beecommerce.dto.reponse.SpecificationResponse;
import com.beecommerce.dto.request.SpecificationRequest;
import com.beecommerce.models.Specification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecificationMapper {
    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    Specification convertToEntity(SpecificationRequest request);
    SpecificationResponse convertToResponse(Specification specification);
}
