package com.beecommerce.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Document(collection = "categories")
@ToString
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
    private List<Category> categories;
}
