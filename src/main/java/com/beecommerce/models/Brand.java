package com.beecommerce.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("brands")
@ToString
public class Brand {
    @Id
    private String id;
    private String name;
    private String description;
    private String image;
}
