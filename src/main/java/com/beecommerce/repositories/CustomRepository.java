package com.beecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;

public interface CustomRepository<T> {
    Page<T> findAllWithCriteriaAndPageable(Class<T> typeParameterClass, Criteria criteria, Pageable pageable);
}
