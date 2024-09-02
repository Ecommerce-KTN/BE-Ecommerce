package com.beecommerce.services;

import com.beecommerce.dto.reponse.CategoryResponse;
import com.beecommerce.dto.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryInterface {
    CategoryResponse createCategory(CategoryRequest category);
    Optional<CategoryResponse>  updateCategory(String id, CategoryRequest category);
    List<CategoryResponse> getAllCategories();
    Optional<CategoryResponse> getCategoryById(String id);
    void deleteCategory(String id);
    List<CategoryResponse> getParentCategories();
    List<CategoryResponse> getCategoriesByParentId(String parentId);
    CategoryResponse createChildCategory(String parentId, CategoryRequest categoryRequest);
}
