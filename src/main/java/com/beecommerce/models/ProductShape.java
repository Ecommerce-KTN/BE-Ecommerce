package com.beecommerce.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class ProductShape {
    private String id;
    private String unitOfMass;
    private String unitOfLength;
    private Double weight;
    private Double breadth;
    private Double width;
    private Double length;
}
