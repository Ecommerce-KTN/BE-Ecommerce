package com.beecommerce.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@ToString
@Document("product_colors")
public class ProductColor {
    @Id
    private String id;
    private String productId;
    private String color;
    private String imageForColor;
}
