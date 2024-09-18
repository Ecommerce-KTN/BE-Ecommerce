package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.ApiResponse;
import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.dto.request.ReviewRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.mapper.ProductMapper;
import com.beecommerce.models.Product;
import com.beecommerce.services.CartService;
import com.beecommerce.services.ProductService;
import com.beecommerce.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.beecommerce.exception.ErrorCode;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private final S3Service s3Service;



    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest request) {
        try {
            Product product = productService.createProduct(ProductMapper.INSTANCE.convertToEntity(request));
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Product created successfully")
                    .data(ProductMapper.INSTANCE.convertToResponse(product))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.PRODUCT_ALREADY_EXISTS.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.PRODUCT_ALREADY_EXISTS.getMessage())
                            .build());
        }
    }


    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            if (products.isEmpty()) {
                return ResponseEntity.status(ErrorCode.PRODUCT_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.PRODUCT_NOT_FOUND.getMessage())
                                .build());
            }
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Products retrieved successfully")
                    .data(products)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.DATABASE_ERROR.getMessage())
                            .build());
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable String id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return ResponseEntity.status(ErrorCode.PRODUCT_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.PRODUCT_NOT_FOUND.getMessage())
                                .build());
            }
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .data(product)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.DATABASE_ERROR.getMessage())
                            .build());
        }
    }


}
