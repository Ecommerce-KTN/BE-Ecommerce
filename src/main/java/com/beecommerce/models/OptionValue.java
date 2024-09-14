package com.beecommerce.models;
import com.beecommerce.models.enums.ProductOption;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OptionValue {
    private ProductOption optionName;
    private String values;
}

