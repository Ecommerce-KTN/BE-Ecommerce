package com.beecommerce.repositories;

import com.beecommerce.models.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductTypeRepository extends MongoRepository<ProductType, String> {

}
