package com.beecommerce.services;

import com.beecommerce.dto.reponse.CategoryResponse;
import com.beecommerce.dto.request.CategoryRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.models.Category;
import com.beecommerce.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryInterface {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryResponse> getCategoryById(String id) {
        return Optional.ofNullable(categoryRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new Exception(ErrorCode.CATEGORY_NOT_FOUND)));
    }
    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        category.setImage(categoryRequest.getImage());
        Category savedCategory = categoryRepository.save(category);
        return convertToResponse(savedCategory);
    }

    @Override
    public Optional<CategoryResponse> updateCategory(String id, CategoryRequest categoryRequest) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception(SuccessCode.CATEGORY_UPDATED));

        existingCategory.setName(categoryRequest.getName());
        existingCategory.setDescription(categoryRequest.getDescription());
        existingCategory.setImage(categoryRequest.getImage());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return Optional.of(convertToResponse(updatedCategory));
    }
        @Override
    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorCode.CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponse> getParentCategories() {
        List<Category> categories = categoryRepository.findByParentIdIsNull();
        if (categories.isEmpty()){
            throw new Exception(ErrorCode.CATEGORY_NOT_FOUND);
        }
        return categories.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponse> getCategoriesByParentId(String parentId) {
        List<Category> categories = categoryRepository.findByParentId(parentId);
        if (categories.isEmpty()){
            throw new Exception(ErrorCode.CATEGORY_NOT_FOUND);
        }
        return categories.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    public Category convertToEntity(CategoryRequest categoryRequest) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryRequest, category);
        return category;
    }
    public CategoryResponse convertToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        BeanUtils.copyProperties(category, response);
        return response;
    }


}
