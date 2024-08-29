package com.beecommerce.services;

import com.beecommerce.models.Product;
import com.beecommerce.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(String id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        BeanUtils.copyProperties(productDetails, product, "id");
        return productRepository.save(product);
    }
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        productRepository.delete(product);
    }

}
