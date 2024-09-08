package com.beecommerce.models;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Data
@Document("products")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    @NotBlank(message = "Product name cannot be empty.")
    @Size(min = 5, max = 120, message = "Product name must be between 5 and 120 characters.")
    private String name;
    private String productTypeId;
    private List<ProductColor> productColors;
    private String size;
    private int quantity;
    private String primaryImage;
    private List<String> images;
    private Double costPrice;
    private Double price;
    private Double discount;
    private String SKU;
    private String sellingType;
    private String unitOfMass;
    private String unitOfLength;
    private Double weight;
    private Double breadth;
    private Double width;
    private Double length;
    private boolean status;
    private String description;
    private String createdDate;
    private String updatedDate;
    private List<String> reviewIds;
    @JsonSerialize(using = DateSerializer.class)
    private Date createdTime;

    public int getNoOfReviews() {
        return reviewIds != null ? reviewIds.size() : 0;
    }

    public Product(String name){
        this.name = name;
    }
}
