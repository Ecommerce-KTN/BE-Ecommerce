package com.beecommerce.services;

import com.beecommerce.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductInterface {
    Product createProduct(Product product);
    Optional<Product> updateProduct(String id, Product product);
    List<Product> getAllProduct();
    Optional<Product> getProductById(String id);
    void deleteProduct(String id);
}
