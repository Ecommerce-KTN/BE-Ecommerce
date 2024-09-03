package com.beecommerce.repositories;

import com.beecommerce.models.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Neo4jRepository<Product, String> {
    Optional<Product> findByName(String name);
    List<Product> findAllByOrderByCreatedTimeDesc();
}
