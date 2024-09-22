package com.beecommerce.mapper;

import com.beecommerce.dto.request.PostReviewRequest;
import com.beecommerce.models.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })

    Review postReviewDtoToReview(PostReviewRequest dto, String userId);
}
