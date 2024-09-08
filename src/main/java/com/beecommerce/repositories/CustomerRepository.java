package com.beecommerce.repositories;

import com.beecommerce.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<User,String> {
}
