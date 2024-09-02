package com.beecommerce.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Data
@Node
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Category {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String name;
    private String description;
    private String image;
    private String parentId;

    @Relationship(type = "HAS_CATEGORY", direction = Relationship.Direction.OUTGOING)
    private List<Category> categories;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private List<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", parentId='" + parentId + '\'' +
                ", categories=" +categories.stream().map(Category::getId).toList() +
                '}';
    }

}
