package com.beecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShapeResponse {
    private String unitOfMass;
    private String unitOfLength;
    private Double weight;
    private Double breadth;
    private Double width;
    private Double length;
}
