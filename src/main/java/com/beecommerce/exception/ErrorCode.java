package com.beecommerce.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public enum ErrorCode {
    PRODUCT_NOT_FOUND(404, "Product not found", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(404,"Category not found", HttpStatus.NOT_FOUND),
    PRODUCT_ALREADY_EXISTS(400, "Product already exists", HttpStatus.BAD_REQUEST),
    CATEGORY_ALREADY_EXISTS(400, "Category already exists", HttpStatus.BAD_REQUEST),
    INVALID_PRODUCT_ID(400, "Invalid product ID", HttpStatus.BAD_REQUEST),
    INVALID_CATEGORY_ID(400, "Invalid category ID", HttpStatus.BAD_REQUEST),
    PRODUCT_OUT_OF_STOCK(400, "Product out of stock", HttpStatus.BAD_REQUEST),
    DATABASE_ERROR(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATE_PRODUCT_ID(409, "Duplicate product ID", HttpStatus.CONFLICT),
    CUSTOMER_NOT_FOUND(404, "Customer not found", HttpStatus.NOT_FOUND),
    PRODUCT_TYPE_NOT_FOUND(404, "Product type not found", HttpStatus.NOT_FOUND),
    COLLECTION_NOT_FOUND(404, "Collection not found", HttpStatus.NOT_FOUND),
    SPECIFICATION_NOT_FOUND(404, "Specification not found", HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(404, "Order not found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(401, "Unauthorized", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(403, "Forbidden", HttpStatus.FORBIDDEN),
    INTERNAL_SERVER_ERROR(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    VARIANT_NOT_FOUND(404, "Variant not found", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
