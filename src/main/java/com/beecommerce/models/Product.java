package com.beecommerce.models;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Node
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    @NotBlank(message = "Product name cannot be empty.")
    @Size(min = 5, max = 120, message = "Product name must be between 5 and 120 characters.")
    private String name;
    private String description;
    private int quantity;
    private String SKU;
    private List<String> images;
    private String primaryImage;
    private Double rating;
    private int sold;
    private String sellingType;
    private String unitOfMass;
    private String unitOfLength;
    private Double weight;
    private Double breadth;
    private Double width;
    private Double length;
    private String parentCategoryId;
    private Double costPrice;
    private Double price;
    private Double discountPrice;
    @CreatedDate
    private LocalDateTime createdTime;

    @Relationship(type = "HAS_CATEGORY", direction = Relationship.Direction.OUTGOING)
    private Category category;


    @Relationship(type = "HAS_ORDER_DETAIL", direction = Relationship.Direction.INCOMING)
    private List<OrderDetail> orderDetails;

    @Relationship(type = "HAS_REVIEW", direction = Relationship.Direction.INCOMING)
    private List<Review> reviews;

    public Product() {
        this.createdTime = LocalDateTime.now();
    }

    public int getNoOfReviews() {
        return reviews != null ? reviews.size() : 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", SKU='" + SKU + '\'' +
                ", images=" + images +
                ", primaryImage='" + primaryImage + '\'' +
                ", rating=" + rating +
                ", sold=" + sold +
                ", sellingType='" + sellingType + '\'' +
                ", unitOfMass='" + unitOfMass + '\'' +
                ", unitOfLength='" + unitOfLength + '\'' +
                ", weight=" + weight +
                ", breadth=" + breadth +
                ", width=" + width +
                ", length=" + length +
                ", parentCategoryId='" + parentCategoryId + '\'' +
                ", category=" + category.getId() +
                ", costPrice=" + costPrice +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", createTime=" + createdTime +
                ", reviews=" + reviews +
                '}';
    }

}
