package com.beecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "Category name cannot be empty.")
    private String name;

    private String description;

    private String image;

    private String parentId;

    private Date createdTime;

    private boolean isFeatured;

    private String banner;

    private String icon;
    private List<String> options;
}
