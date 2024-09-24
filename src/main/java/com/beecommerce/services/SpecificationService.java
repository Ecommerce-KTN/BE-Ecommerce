package com.beecommerce.services;

import com.beecommerce.models.Specification;
import com.beecommerce.repositories.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecificationService {
    @Autowired
    private SpecificationRepository specificationRepository;

    public List<Specification>
    getSpecificationsByCategoryId(String categoryId) {
        return specificationRepository.findByCategoryId(categoryId);
    }
}
