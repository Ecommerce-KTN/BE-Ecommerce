package com.beecommerce.dto.request;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    private String name;
    private String description;
    private String image;
    private List<String> productIds;
}