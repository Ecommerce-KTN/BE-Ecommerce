package com.beecommerce.dto.reponse;

import com.beecommerce.models.Category;
import com.beecommerce.models.Product;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryResponse {
    private String id;
    private String name;
    private String description;
    private String image;
    private String parentId;
    private Date createdTime;
//    private List<Product> products;
//    private List<Category> categories;
}