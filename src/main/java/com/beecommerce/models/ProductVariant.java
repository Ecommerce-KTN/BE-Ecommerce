package com.beecommerce.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVariant {
    @Id
    private String id;
    private String value;
    private Double basePrice;
    private Double discountPrice;
    private Double price;
    private String SKU;
    private List<String> image;
    private long quantity;
    private long sold;
    private long view;
    private List<Review> reviews;
    private Double rating;
    private List<OptionValue> optionValue;
}
