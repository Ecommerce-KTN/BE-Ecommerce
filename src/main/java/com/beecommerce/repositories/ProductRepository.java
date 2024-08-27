package com.beecommerce.repositories;

import com.beecommerce.models.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProductRepository extends Neo4jRepository<Product, String> {
}
