package com.beecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {
    private int statusCode;
    private String message;

    public SuccessResponse(SuccessCode successCode) {
        this.statusCode = successCode.getCode();
        this.message = successCode.getMessage();
    }
}