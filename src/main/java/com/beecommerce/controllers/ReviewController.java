package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.ApiResponse;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.models.Product;
import com.beecommerce.models.ProductVariant;
import com.beecommerce.models.Review;
import com.beecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/{productId}/variant/{variantId}")
    public ResponseEntity<ApiResponse> addReviewToVariant(@PathVariable String productId,
                                                          @PathVariable String variantId,
                                                          @RequestBody Review review) {
        try {
            // Lấy sản phẩm theo productId
            Product product = productService.getProductById(productId);

            // Tìm biến thể (variant) dựa vào variantId
            ProductVariant productVariant = productService.findVariantById(product, variantId);

            if (productVariant != null) {
                // Thêm review vào biến thể sản phẩm
                productVariant.getReviews().add(review);

                // Lưu lại sản phẩm với review mới
                productService.saveProduct(product);

                return ResponseEntity.ok(ApiResponse.builder()
                        .success(true)
                        .message("Review added successfully")
                        .data(review)
                        .build());
            } else {
                return ResponseEntity.status(404).body(ApiResponse.builder()
                        .success(false)
                        .message(ErrorCode.VARIANT_NOT_FOUND.getMessage())
                        .build());
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body(ApiResponse.builder()
                    .success(false)
                    .message(ErrorCode.DATABASE_ERROR.getMessage())
                    .build());
        }
    }
}
