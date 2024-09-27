package com.beecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShapeRequest {

    private String unitOfMass;

    private String unitOfLength;

    private Double weight;

    private Double breadth;

    private Double width;

    private Double length;
}
