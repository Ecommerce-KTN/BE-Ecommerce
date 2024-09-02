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
    private String parentId;
    private List<String> productIds;
    private List<String> categoryIds;
}