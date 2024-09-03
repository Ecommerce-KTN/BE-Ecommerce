package com.beecommerce.dto.reponse;

import com.beecommerce.models.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private List<String> images;
    private String primaryImage;
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
    private String parentCategoryName;
    private String categoryId;
    private String categoryName;
    private Double costPrice;
    private Double price;
    private Double discountPrice;
    private LocalDateTime createTime;
//    private List<OrderDetail> orderDetails;


    public ProductResponse(Product product) {
    }
}