package com.beecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationResponse {
    private String id;
    private String name;
    private String value;
    private String categoryId;
}
