package com.beecommerce.dto.request;

import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.models.enums.SpecificationOption;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile image;
    private String parentId;
    private Date createdTime;
    private String banner;
    private String icon;
    private List<String> options;
    private List<String> specifications;
}
