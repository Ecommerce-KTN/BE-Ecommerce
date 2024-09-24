package com.beecommerce.repositories;

import com.beecommerce.models.Product;
import com.beecommerce.models.Specification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationRepository extends MongoRepository<Specification, String> {
    List<Specification> findByCategoryId(String categoryId);

}
