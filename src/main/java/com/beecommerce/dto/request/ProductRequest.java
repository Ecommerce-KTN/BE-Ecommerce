package com.beecommerce.dto.request;
import com.beecommerce.models.CostPrice;
import com.beecommerce.models.DiscountPrice;
import com.beecommerce.models.OrderDetail;
import com.beecommerce.models.Price;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private String name;
    private String description;
    private List<MultipartFile> images;
    private MultipartFile primaryImage;
    private Double rating;
    private int sold;
    private int quantity;
    private String sellingType;
    private Double weight;
    private Double breadth;
    private Double width;
    private Double length;
    private String unitOfMass;
    private String unitOfLength;
    private String parentCategoryId;
    private String categoryId;
    private Double costPrice;
    private Double price;
    private Double discountPrice;
    private List<OrderDetail> orderDetails;

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", primaryImage=" + primaryImage +
                ", rating=" + rating +
                ", sold=" + sold +
                ", quantity=" + quantity +
                ", sellingType='" + sellingType + '\'' +
                ", weight=" + weight +
                ", breadth=" + breadth +
                ", width=" + width +
                ", length=" + length +
                ", unitOfMass='" + unitOfMass + '\'' +
                ", unitOfLength='" + unitOfLength + '\'' +
                ", parentCategoryId='" + parentCategoryId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", costPrice=" + costPrice +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
