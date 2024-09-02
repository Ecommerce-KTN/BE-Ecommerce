package com.beecommerce.repositories;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import com.beecommerce.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends Neo4jRepository<Category, String> {
    Optional<Category> findByName(String name);
    List<Category> findByParentIdIsNull();
    List<Category> findByParentId(String parentId);
}