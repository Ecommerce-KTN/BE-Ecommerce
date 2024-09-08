package com.beecommerce.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DiscountPriceRepository extends Neo4jRepository<DiscountPrice, String> {

}
