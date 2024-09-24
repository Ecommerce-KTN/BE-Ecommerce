package com.beecommerce.dto.reponse;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SpecificationResponse {
    private String id;
    private String name;
    private String value;
    private String categoryId;
}
