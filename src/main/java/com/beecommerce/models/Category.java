package com.beecommerce.models;

import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.models.enums.SpecificationOption;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Document(collection = "categories")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Category {
    @Id
    private String id;
    @NotBlank
    private String name;
    private String description;
    private String image;
    private String parentId;
    @JsonSerialize(using = DateSerializer.class)
    private Date createdTime;
    private String banner;
    private String icon;
    @Field("options")
    private List<String> options;

    public List<ProductOption> getOptions() {
        return options.stream()
                .map(ProductOption::fromDisplayName)
                .toList();
    }

    public void setOptions(List<ProductOption> options) {
        this.options = options.stream()
                .map(ProductOption::getDisplayName)
                .toList();
    }

    @Field("specifications")
    private List<String> specifications;

    public List<SpecificationOption> getSpecifications() {
        return specifications.stream()
                .map(SpecificationOption::fromDisplayName)
                .toList();
    }

    public void setSpecifications(List<SpecificationOption> specifications) {
        this.specifications = specifications.stream()
                .map(SpecificationOption::getDisplayName)
                .toList();
    }
}
