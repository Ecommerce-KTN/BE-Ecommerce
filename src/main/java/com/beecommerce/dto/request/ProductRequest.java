package com.beecommerce.dto.request;
import com.beecommerce.models.CostPrice;
import com.beecommerce.models.DiscountPrice;
import com.beecommerce.models.OrderDetail;
import com.beecommerce.models.Price;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private String name;
    private String description;
    private String brand;
    private List<String> image;
    private String primaryImage;
    private Double rating;
    private int sold;
    private String categoryIds;
    private List<CostPrice> costPrices;
    private List<Price> prices;
    private List<DiscountPrice> discountPrices;
    private List<OrderDetail> orderDetails;
}
