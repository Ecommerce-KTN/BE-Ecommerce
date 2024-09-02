package com.beecommerce.repositories;

import com.beecommerce.models.Price;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PriceRepository extends Neo4jRepository<Price, String> {

}
