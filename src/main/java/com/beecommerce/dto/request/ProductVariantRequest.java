package com.beecommerce.dto.request;

import com.beecommerce.models.Review;
import com.beecommerce.models.enums.ProductOption;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariantRequest {
    @Id
    private String id;
    private Double basePrice;
    private Double discountPrice;
    private Double price;
    private String SKU;
    private List<MultipartFile> images;
    private long quantity;
    private Map<String, String> attributes;
    private long sold;
}
