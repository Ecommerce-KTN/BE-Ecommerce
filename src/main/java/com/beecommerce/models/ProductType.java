package com.beecommerce.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@ToString
@Document("product_types")
public class ProductType {
    @Id
    private String id;
    private String parentCategoryId;
    private String childCategoryId;
    @JsonSerialize(using = DateSerializer.class)
    private Date createdTime;
    private String categoryId;
    private List<String> productIds;
}
