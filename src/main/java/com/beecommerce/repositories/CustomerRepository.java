package com.beecommerce.repositories;

import com.beecommerce.models.Customer;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CustomerRepository extends Neo4jRepository<Customer,String> {
}
