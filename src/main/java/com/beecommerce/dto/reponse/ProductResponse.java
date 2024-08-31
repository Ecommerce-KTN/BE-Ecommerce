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
    private int numberOfReviews;
    private Double oldPrice;
    private Double currentPrice;
    private List<CategoryResponse> categories;
    private List<ImportPrice> importPrices;
    private List<SalePrice> salePrices;
    private Supplier supplier;
    private List<OrderDetail> orderDetails;
}