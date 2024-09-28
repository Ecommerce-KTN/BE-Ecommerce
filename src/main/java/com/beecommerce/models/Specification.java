package com.beecommerce.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "specifications")
public class Specification {
    private String id;
    private String name;
    private String value;
    private String categoryId;

}
