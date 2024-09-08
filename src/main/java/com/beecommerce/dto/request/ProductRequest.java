package com.beecommerce.dto.request;

import com.beecommerce.dto.request.ProductColorRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    @NotBlank(message = "Product name cannot be empty.")
    @Size(min = 5, max = 120, message = "Product name must be between 5 and 120 characters.")
    private String name;

    private String productTypeId;

    private List<ProductColorRequest> productColors;

    private String size;

    @NotNull(message = "Quantity cannot be null.")
    private Integer quantity;

    private String primaryImage;

    private List<String> images;

    @NotNull(message = "Cost price cannot be null.")
    private Double costPrice;

    @NotNull(message = "Price cannot be null.")
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

    private Boolean status;

    private String description;

    private String categoryId;

    private List<String> reviewIds;
}
