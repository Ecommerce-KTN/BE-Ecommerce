package com.beecommerce.repositories;
import com.beecommerce.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
//    Optional<Category> findByName(String name);
//    List<Category> findByParentIdIsNull();
//    List<Category> findByParentId(String parentId);
//    List<Category> findAllByOrderByCreatedTimeDesc();
}