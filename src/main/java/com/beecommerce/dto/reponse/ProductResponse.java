package com.beecommerce.dto.reponse;

import com.beecommerce.models.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private String brand;
    private List<String> image;
    private String primaryImage;
    private Double rating;
    private int sold;
    private Category categories;
    private List<CostPrice> importPrices;
    private List<Price> prices;
    private List<DiscountPrice> discountPrices;
    private List<OrderDetail> orderDetails;

    public ProductResponse(Product product) {
    }
}