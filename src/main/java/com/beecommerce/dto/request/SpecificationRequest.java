package com.beecommerce.dto.request;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SpecificationRequest {
    private String id;
    private String name;
    private String value;
    private String categoryId;
}
