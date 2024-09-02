package com.beecommerce.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public enum SuccessCode {
    PRODUCT_CREATED(201, "Product created successfully", HttpStatus.CREATED),
    PRODUCT_UPDATED(200, "Product updated successfully", HttpStatus.OK),
    PRODUCT_DELETED(200, "Product deleted successfully", HttpStatus.OK),
    CATEGORY_CREATED(201, "Category created successfully", HttpStatus.CREATED),
    CATEGORY_UPDATED(200, "Category updated successfully", HttpStatus.OK),
    CATEGORY_DELETED(200, "Category deleted successfully", HttpStatus.OK),
    PRODUCT_ADD_REVIEW(200, "Product add Review successfully", HttpStatus.OK);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    SuccessCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}