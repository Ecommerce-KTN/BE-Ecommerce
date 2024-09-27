package com.beecommerce.controllers;

import com.beecommerce.dto.response.ApiResponse;
import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.dto.request.ProductVariantRequest;
import com.beecommerce.dto.response.ApiResponse;
import com.beecommerce.dto.response.PaginatedResource;
import com.beecommerce.dto.response.ProductResponse;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.mapper.ProductMapper;
import com.beecommerce.models.Product;
import com.beecommerce.dto.request.GetProductReviewsRequest;
import com.beecommerce.models.Review;
import com.beecommerce.models.Specification;
import com.beecommerce.models.enums.ProductOption;
import com.beecommerce.services.CartService;
import com.beecommerce.services.ProductService;
import com.beecommerce.services.ReviewService;
import com.beecommerce.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private ReviewService reviewService;
    @Autowired
    private final S3Service s3Service;

    @Autowired
    private final ProductMapper productMapper;


    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ProductResponse> addProduct(@ModelAttribute ProductRequest productRequest) throws IOException {
        // Upload primary image to S3
        String primaryImageUrl = null;
        if (productRequest.getPrimaryImage() != null && !productRequest.getPrimaryImage().isEmpty()) {
            primaryImageUrl = s3Service.uploadFileToS3(productRequest.getPrimaryImage());
        }

        // Upload product images to S3
        List<String> imageUrls = new ArrayList<>();
        if (productRequest.getImages() != null && !productRequest.getImages().isEmpty()) {
            imageUrls = productRequest.getImages().stream()
                    .map(image -> s3Service.uploadFileToS3(image))
                    .collect(Collectors.toList());
        }

        // Map request to entity
        Product product = productMapper.toProduct(productRequest);
        product.setPrimaryImage(primaryImageUrl);  // Set primary image URL
        product.setImages(imageUrls);  // Set uploaded image URLs

        // Handle product variants images
        if (productRequest.getProductVariants() != null) {
            productRequest.getProductVariants().forEach(variantRequest -> {
                List<String> variantImageUrls;
                if (variantRequest.getImages() != null && !variantRequest.getImages().isEmpty()) {
                    variantImageUrls = variantRequest.getImages().stream()
                            .map(image -> s3Service.uploadFileToS3(image))
                            .collect(Collectors.toList());
                } else {
                    variantImageUrls = new ArrayList<>();
                }

                product.getProductVariants().stream()
                        .filter(v -> v.getSKU().equals(variantRequest.getSKU()))
                        .forEach(v -> v.setImages(variantImageUrls));
            });
        }
        // Save product
        Product savedProduct = productService.createProduct(product);

        // Map entity to response DTO
        ProductResponse productResponse = productMapper.toProductResponse(savedProduct);

        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
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

//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
//        try {
//            Optional<Product> updatedProduct = productService.updateProduct(id, productRequest);
//
//            return ResponseEntity.ok(ApiResponse.builder()
//                    .success(true)
//                    .message(SuccessCode.PRODUCT_UPDATED.getMessage())
//                    .data(ProductMapper.INSTANCE.convertToResponse(updatedProduct.get()))
//                    .build());
//        } catch (Exception e) {
//            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
//                    .body(ApiResponse.builder()
//                            .success(false)
//                            .message(e.getMessage())
//                            .build());
//        }
//    }

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

    @GetMapping("/{productId}/related")
    public ResponseEntity<ApiResponse> getRelatedProducts(@PathVariable String productId) {
        try {
            Product product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.status(ErrorCode.PRODUCT_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.PRODUCT_NOT_FOUND.getMessage())
                                .build());
            }

            String categoryId = product.getCategories().get(0).getId();

            List<Product> relatedProducts = productService.getProductsByCategoryExcludingProduct(categoryId, productId);

            if (relatedProducts.isEmpty()) {
                return ResponseEntity.status(ErrorCode.PRODUCT_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message("No related products found")
                                .build());
            }

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Related products retrieved successfully")
                    .data(relatedProducts)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.DATABASE_ERROR.getMessage())
                            .build());
        }
    }


    @PostMapping("{productId}/reviews")
    public ResponseEntity<?> getProductReviews(
            @PathVariable("productId") String productId,
            @RequestBody GetProductReviewsRequest dto,
            @PageableDefault(page = 0, size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            ) {
        System.out.println(pageable);
        PaginatedResource<Review> result = reviewService.getProductReviewsPaginated(productId, dto, pageable);
        return new ResponseEntity<>(new ApiResponse<Object>(result, "Success", HttpStatus.OK.value(), true, null), HttpStatus.OK);
    }
//    @GetMapping("/specifications")
//    public ResponseEntity<List<Specification>> getSpecificationsByCategoryId(@RequestParam String categoryId) {
//        List<Specification> specifications = productService.getSpecificationsByCategoryId(categoryId);
//        return ResponseEntity.ok(specifications);
//    }

}
