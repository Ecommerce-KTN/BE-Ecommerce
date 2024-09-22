package com.beecommerce.repositories;

import com.beecommerce.models.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectionRepository extends MongoRepository<Collection, String> {
}
