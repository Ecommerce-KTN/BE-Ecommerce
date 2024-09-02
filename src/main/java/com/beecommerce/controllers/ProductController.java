package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.ProductResponse;
import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.dto.request.ReviewRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.models.Product;
import com.beecommerce.services.CartService;
import com.beecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        ProductResponse createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest productDetails) {
        ProductResponse updatedProduct = productService.updateProduct(id, productDetails)
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        ProductResponse product = productService.getProductById(id)
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/favorite")
    public ResponseEntity<ProductResponse> toggleFavorite(@PathVariable String id) {
        return productService.toggleFavorite(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @GetMapping("/check-stock")
    public ResponseEntity<String> checkStockAvailability( @RequestParam("productId") String productId,
    @RequestParam("quantity") int quantity) {
        String message = productService.checkStockAvailability(productId, quantity);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addItemToCart(@RequestParam("productId") String productId, @RequestParam("quantity") int quantity){
        String message = cartService.addItemToCart(productId, quantity);
        return ResponseEntity.ok(message);
    }
    @GetMapping("/check-name-unique")
    public ResponseEntity<Boolean> checkProductNameUnique(@RequestParam("name") String name) {
        boolean isUnique = productService.isProductNameUnique(name);
        return ResponseEntity.ok(isUnique);
    }

    @PostMapping("/add-review")
    public ResponseEntity<String> addReviewToProduct(@RequestBody ReviewRequest reviewRequest) {
        productService.addReviewToProduct(reviewRequest);
        return ResponseEntity.status(SuccessCode.PRODUCT_ADD_REVIEW.getStatusCode())
                .body(SuccessCode.PRODUCT_ADD_REVIEW.getMessage());
    }

}
