package com.beecommerce.dto.request;
import com.beecommerce.models.ImportPrice;
import com.beecommerce.models.OrderDetail;
import com.beecommerce.models.SalePrice;
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
    private int numberOfReviews;
    private Double oldPrice;
    private Double currentPrice;
    private List<String> categoryIds;
    private List<ImportPrice> importPrices;
    private List<SalePrice> salePrices;
    private String supplierId;
    private List<OrderDetail> orderDetails;
}
