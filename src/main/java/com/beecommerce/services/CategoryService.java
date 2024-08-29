package com.beecommerce.services;

import com.beecommerce.models.Category;
import com.beecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

//    public Optional<Category> getCategoryByName(String name) {
//        return categoryRepository.findByName(name);
//    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(String id, Category categoryDetails) {
        Category existingCategory = getCategoryById(id);  // Reuse the existing method
        // Update fields
        existingCategory.setName(categoryDetails.getName());
        existingCategory.setDescription(categoryDetails.getDescription());
        existingCategory.setImage(categoryDetails.getImage());
        existingCategory.setProducts(categoryDetails.getProducts());

        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(String id) {
        Category existingCategory = getCategoryById(id);  // Reuse the existing method
        categoryRepository.delete(existingCategory);
    }
}
