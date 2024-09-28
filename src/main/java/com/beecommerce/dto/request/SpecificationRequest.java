package com.beecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationRequest {
    private String id;
    private String name;
    private String value;
    private String categoryId;
}
