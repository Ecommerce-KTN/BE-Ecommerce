package com.beecommerce.dto.request;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionValueRequest {
    private String optionName;
    private String values;
}
