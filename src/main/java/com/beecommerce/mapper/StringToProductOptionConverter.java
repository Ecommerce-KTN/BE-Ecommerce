package com.beecommerce.mapper;

import com.beecommerce.models.enums.ProductOption;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProductOptionConverter implements Converter<String, ProductOption> {
    @Override
    public ProductOption convert(String source) {
        try {
            return ProductOption.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid ProductOption: " + source);
        }
    }
}
