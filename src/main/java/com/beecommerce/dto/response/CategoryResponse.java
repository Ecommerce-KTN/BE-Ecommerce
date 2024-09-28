package com.beecommerce.dto.response;

import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.models.enums.SpecificationOption;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private String id;
    private String name;
    private String description;
    private String image;
    private String parentId;
    private Date createdTime;
    private String banner;
    private String icon;
    @Builder.Default
    private List<String> options = new ArrayList<>();
    @Builder.Default
    private List<String> specifications = new ArrayList<>();
}
