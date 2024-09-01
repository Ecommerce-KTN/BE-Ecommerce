package com.beecommerce.repositories;

import com.beecommerce.models.OrderDetail;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrderDetailRepository extends Neo4jRepository<OrderDetail, String> {
}
