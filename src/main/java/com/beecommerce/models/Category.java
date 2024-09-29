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
import java.util.ArrayList;
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
    private List<ProductOption> options;
    private List<SpecificationOption> specifications;

}
