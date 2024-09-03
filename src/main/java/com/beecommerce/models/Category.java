package com.beecommerce.models;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Node
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter

public class Category {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    @NotBlank
    private String name;
    private String description;
    private String image;
    private String parentId;
    @CreatedDate
    private LocalDateTime createdTime;
    @Relationship(type = "HAS_CATEGORY", direction = Relationship.Direction.OUTGOING)
    private List<Category> categories;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private List<Product> products;

    public Category() {
        this.createdTime = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", parentId='" + parentId + '\'' +
                ", createdTime=" + createdTime +
                ", categories=" + (categories != null ? categories.stream().map(Category::getId).toList() : null) +
                '}';
    }

}
