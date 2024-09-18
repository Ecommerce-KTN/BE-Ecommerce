package com.beecommerce.repositories.impl;

import com.beecommerce.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepositoryImpl<T> implements CustomRepository<T> {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<T> findAllWithCriteriaAndPageable(Class<T> typeParameterClass, Criteria criteria, Pageable pageable) {
        Query query = new Query(criteria).with(pageable);
        List<T> items = mongoTemplate.find(query, typeParameterClass);
        return PageableExecutionUtils.getPage(
                items,
                pageable,
                () -> mongoTemplate.count(query, typeParameterClass)
        );
    }
}
