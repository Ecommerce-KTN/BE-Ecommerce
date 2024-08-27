package com.beecommerce.services;

import com.beecommerce.models.Category;
import com.beecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
