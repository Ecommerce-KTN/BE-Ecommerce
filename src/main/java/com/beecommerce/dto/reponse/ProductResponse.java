package com.beecommerce.dto.response;

import com.beecommerce.dto.reponse.CategoryResponse;
import com.beecommerce.models.OptionValue;
import com.beecommerce.models.ProductShape;
import com.beecommerce.models.ProductVariant;
import com.beecommerce.models.Specification;
import lombok.*;

import java.awt.*;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String id;

    private String name;

    private String primaryImage;

    private String Brand;

    private List<String> images;

    private String sellingType;

    private ProductShape shape;

    private String description;

    private Double basePrice;

    private Double discountPrice;

    private Double price;

    private Date createdTime;

    private List<CategoryResponse> categories;

    private List<Specification> specifications;

    private List<OptionValue> optionValues;

    private List<ProductVariant> productVariants;
}
