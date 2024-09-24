package com.beecommerce.mapper;

import com.beecommerce.dto.reponse.ReviewResponse;
import com.beecommerce.dto.request.ReviewRequest;
import com.beecommerce.models.Review;
import org.mapstruct.factory.Mappers;

public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review convertToEntity(ReviewRequest reviewRequest);

    ReviewResponse convertToResponse(Review review);
}
