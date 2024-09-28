package com.beecommerce.dto.response;

import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.models.enums.SpecificationOption;
import lombok.*;

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
    private List<String> options;
    private List<String> specifications;
}
