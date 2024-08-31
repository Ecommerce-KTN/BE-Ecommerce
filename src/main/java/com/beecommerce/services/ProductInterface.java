package com.beecommerce.services;

import com.beecommerce.dto.reponse.ProductResponse;
import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductInterface {
    ProductResponse createProduct(ProductRequest product);
    Optional<ProductResponse> updateProduct(String id, ProductRequest product);
    List<ProductResponse> getAllProduct();
    Optional<ProductResponse> getProductById(String id);
    void deleteProduct(String id);
}