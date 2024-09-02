package com.beecommerce.repositories;

import com.beecommerce.models.Review;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ReviewRepository extends Neo4jRepository<Review, String> {
}
