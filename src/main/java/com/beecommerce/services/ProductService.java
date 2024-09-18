package com.beecommerce.services;


import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.dto.request.ReviewRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.models.*;
import com.beecommerce.repositories.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private  ProductRepository productRepository;


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
}
