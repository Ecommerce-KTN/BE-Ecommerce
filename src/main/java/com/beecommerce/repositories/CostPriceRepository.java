package com.beecommerce.repositories;


import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CostPriceRepository extends Neo4jRepository<CostPrice, String> {

}
