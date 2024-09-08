package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.ApiResponse;
import com.beecommerce.dto.reponse.ProductResponse;
import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.dto.request.ReviewRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.services.CartService;
import com.beecommerce.services.ProductService;
import com.beecommerce.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


//    @PostMapping
//    public ResponseEntity<?> createProduct(@ModelAttribute ProductRequest productRequest) {
//        if (productRequest.getPrimaryImage().isEmpty()) {
//            return ResponseEntity.badRequest().body(ApiResponse.builder()
//                    .status(400)
//                    .message("Primary Image is required")
//                    .build());
//        }
//        if (productRequest.getPrimaryImage().getSize() > 10 * 1024 * 1024) {
//            return ResponseEntity.badRequest().body(ApiResponse.builder()
//                    .status(400)
//                    .message("Primary Image size must be less than 10MB")
//                    .build());
//        }

        // Validate image list
//        if (productRequest.getImages().size() > 10) {
//            return ResponseEntity.badRequest().body(ApiResponse.builder()
//                    .status(400)
//                    .message("Number of images must be less than 10")
//                    .build());
//        }
//        for (MultipartFile image : productRequest.getImages()) {
//            if (image.getSize() > 10 * 1024 * 1024) {
//                return ResponseEntity.badRequest().body(ApiResponse.builder()
//                        .status(400)
//                        .message("Image size must be less than 10MB")
//                        .build());
//            }
//        }

        // Upload images to S3
//        List<String> imageUrls = new ArrayList<>();
//        if(productRequest.getImages().size() >0) {
//            for (MultipartFile image : productRequest.getImages()) {
//                if(!image.isEmpty() ){
//                    String imageUrl;
//                    try {
//                        imageUrl = s3Service.uploadFileToS3(image);
//                    } catch (Exception e) {
//                        return ResponseEntity.badRequest().body(ApiResponse.builder()
//                                .status(400)
//                                .message(e.getMessage())
//                                .build());
//                    }
//                    imageUrls.add(imageUrl);
//                }
//
//            }
//        }


//        String primaryImageUrl;
//        try {
//            primaryImageUrl = s3Service.uploadFileToS3(productRequest.getPrimaryImage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(ApiResponse.builder()
//                    .status(400)
//                    .message(e.getMessage())
//                    .build());
//        }

        // Create product
//        ProductResponse createdProduct = productService.createProduct(productRequest, primaryImageUrl, imageUrls);
//        return ResponseEntity.badRequest().body(ApiResponse.builder()
//                .status(400)
//                .data(createdProduct)
//                .build());
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest productDetails) {
//        ProductResponse updatedProduct = productService.updateProduct(id, productDetails)
//                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    @GetMapping
//    public List<ProductResponse> getAllProducts() {
//        return productService.getAllProduct();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
//        ProductResponse product = productService.getProductById(id)
//                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
//        return ResponseEntity.ok(product);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
//    @PostMapping("/{id}/favorite")
//    public ResponseEntity<ProductResponse> toggleFavorite(@PathVariable String id) {
//        return productService.toggleFavorite(id)
//                .map(ResponseEntity::ok)
//                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
//    }
//
//    @GetMapping("/check-stock")
//    public ResponseEntity<String> checkStockAvailability( @RequestParam("productId") String productId,
//    @RequestParam("quantity") int quantity) {
//        String message = productService.checkStockAvailability(productId, quantity);
//        return ResponseEntity.ok(message);
//    }
//
//    @PostMapping("/add-to-cart")
//    public ResponseEntity<String> addItemToCart(@RequestParam("productId") String productId, @RequestParam("quantity") int quantity){
//        String message = cartService.addItemToCart(productId, quantity);
//        return ResponseEntity.ok(message);
//    }
//    @GetMapping("/check-name-unique")
//    public ResponseEntity<Boolean> checkProductNameUnique(@RequestParam("name") String name) {
//        boolean isUnique = productService.isProductNameUnique(name);
//        return ResponseEntity.ok(isUnique);
//    }

//    @PostMapping("/add-review")
//    public ResponseEntity<String> addReviewToProduct(@RequestBody ReviewRequest reviewRequest) {
//        productService.addReviewToProduct(reviewRequest);
//        return ResponseEntity.status(SuccessCode.PRODUCT_ADD_REVIEW.getStatusCode())
//                .body(SuccessCode.PRODUCT_ADD_REVIEW.getMessage());
//    }

}
