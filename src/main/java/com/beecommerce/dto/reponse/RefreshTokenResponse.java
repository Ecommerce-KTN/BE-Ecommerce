package com.beecommerce.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class RefreshTokenResponse {
    private String accessToken;
}
