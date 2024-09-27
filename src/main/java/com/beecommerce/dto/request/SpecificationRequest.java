package com.beecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationRequest {
    @NotBlank
    private String key;

    @NotBlank
    private String value;
}
