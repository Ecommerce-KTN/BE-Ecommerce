package com.beecommerce.services;


import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.models.*;
import com.beecommerce.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private  ProductRepository productRepository;

//    @Autowired
//    private ProductTypeRepository productTypeRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public Optional<Product> updateProduct(String id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));

        BeanUtils.copyProperties(productRequest, existingProduct, "id");

        Product updatedProduct = productRepository.save(existingProduct);

        return Optional.of(updatedProduct);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> getLatest20Products() {
        return productRepository.findAll().stream()
                .sorted((p1, p2) -> p2.getCreatedTime().compareTo(p1.getCreatedTime()))
                .limit(20)
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategory(String categoryId) {
        return productRepository.findByCategories_Id(categoryId);
    }

    public List<Product> getTopSellingProducts() {
        return productRepository.findTop20ByOrderByProductVariants_SoldDesc(PageRequest.of(0, 20));
    }


}
