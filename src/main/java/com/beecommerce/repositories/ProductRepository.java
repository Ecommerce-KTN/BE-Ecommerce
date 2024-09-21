package com.beecommerce.repositories;

import com.beecommerce.models.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

public interface ProductRepository extends MongoRepository<Product, String> {
//    Optional<Product> findByName(String name);
//    List<Product> findAllByOrderByCreatedTimeDesc();
//    get
//    Product createProduct(Product product);
    List<Product> findByCategories_Id(String categoryId);
    List<Product> findTop20ByOrderByProductVariants_SoldDesc(PageRequest pageRequest);
}
