package com.beecommerce.services;

import com.beecommerce.models.Category;
import java.util.List;

public interface CategoryInterface {
    Category createCategory(Category category);
    Category updateCategory(String id, Category category);
    List<Category> getAllCategories();
    Category getCategoryById(String id);
    void deleteCategory(String id);
}
