package com.beecommerce.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("shops")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Shop {
    private String id;
    private String name;
    private String description;
    private String image;
    private String banner;
}
