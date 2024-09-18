package com.beecommerce.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetProductReviewsRequest {
//    @NotNull(message = "minRating rating cannot be null.")
    private Double minRating;
//    @NotNull(message = "maxRating cannot be null.")
    private Double maxRating;
//    @PastOrPresent(message = "fromDate must be in past or present.")
    private Date fromDate;
//    @PastOrPresent(message = "toDate must be in past or present.")
    private Date toDate;
}
