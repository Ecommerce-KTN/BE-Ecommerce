package com.beecommerce.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

public class Collection {
    @Id
    private String id;

    private String name;

    private String banner;

    private String brand;

    private List<Product> products;

    @JsonSerialize(using = DateSerializer.class)
    private Date createdTime;

    private boolean isFeatured;

    private boolean isPaidForHomeLanding;
}
