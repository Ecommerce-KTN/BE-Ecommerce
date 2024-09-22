package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.ApiResponse;
import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.dto.request.ReviewRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.mapper.ProductMapper;
import com.beecommerce.models.Product;
import com.beecommerce.models.Specification;
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
import java.util.Optional;

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
            Product product = ProductMapper.INSTANCE.convertToEntity(request);

            product = productService.createProduct(product);

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .data(ProductMapper.INSTANCE.convertToResponse(product))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.PRODUCT_ALREADY_EXISTS.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message("Error creating product: " + e.getMessage())
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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        try {
            Optional<Product> updatedProduct = productService.updateProduct(id, productRequest);

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message(SuccessCode.PRODUCT_UPDATED.getMessage())
                    .data(ProductMapper.INSTANCE.convertToResponse(updatedProduct.get()))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return ResponseEntity.status(ErrorCode.PRODUCT_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.PRODUCT_NOT_FOUND.getMessage())
                                .build());
            }

            productService.deleteProduct(id);

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message(SuccessCode.PRODUCT_DELETED.getMessage())
                    .build());

        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.DATABASE_ERROR.getMessage())
                            .build());
        }


    }

    @GetMapping("/latest")
    public ResponseEntity<ApiResponse> getLatest20Products() {
        try {
            List<Product> products = productService.getLatest20Products();
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .data(products)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .build());
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable String categoryId) {
        try {
            List<Product> products = productService.getProductsByCategory(categoryId);
            if (products.isEmpty()) {
                return ResponseEntity.status(ErrorCode.PRODUCT_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .build());
            }
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
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

    @GetMapping("/top-selling")
    public ResponseEntity<ApiResponse> getTopSellingProducts() {
        try {
            List<Product> products = productService.getTopSellingProducts();
            if (products.isEmpty()) {
                return ResponseEntity.status(ErrorCode.PRODUCT_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message("No products found")
                                .build());
            }
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
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

    @GetMapping("/specifications")
    public ResponseEntity<List<Specification>> getSpecificationsByCategoryId(@RequestParam String categoryId) {
        List<Specification> specifications = productService.getSpecificationsByCategoryId(categoryId);
        return ResponseEntity.ok(specifications);
    }

}
