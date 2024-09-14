package com.beecommerce.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Specification {
    private String id;
    private String name;
    private String value;
}
