package com.beecommerce.repositories;

import com.beecommerce.models.Specification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpecificationRepository extends MongoRepository<Specification, String>{
//    findByCategoryId
    Specification findByCategoryId(String categoryId);
}
