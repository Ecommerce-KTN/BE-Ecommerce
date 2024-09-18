package com.beecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FavoriteProductRequest {
    public enum Action {
        ADD, REMOVE
    }

    private String productId;
    private Action action;
}
