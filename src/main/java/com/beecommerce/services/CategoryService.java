package com.beecommerce.services;

import com.beecommerce.dto.request.CategoryRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.models.Category;
import com.beecommerce.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.List;

@Service
public class CategoryService implements CategoryInterface {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public Category getCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorCode.CATEGORY_NOT_FOUND));
    }

    public List<Category> get10Categories() {
        return categoryRepository.findAll(PageRequest.of(0, 10)).getContent();
    }

    public Optional<Category> updateCategory(String id, CategoryRequest categoryRequest) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorCode.CATEGORY_NOT_FOUND));

        BeanUtils.copyProperties(categoryRequest, existingCategory, "id");

        Category updatedCategory = categoryRepository.save(existingCategory);

        return Optional.of(updatedCategory);
    }

    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }

    public Category createSubCategory(String parentCategoryId, CategoryRequest subCategoryRequest) {
        Category parentCategory = categoryRepository.findById(parentCategoryId)
                .orElseThrow(() -> new Exception(ErrorCode.CATEGORY_NOT_FOUND));

        Category subCategory = new Category();
        BeanUtils.copyProperties(subCategoryRequest, subCategory); // Copy dữ liệu từ request sang entity
        subCategory.setParentId(parentCategoryId); // Gán parentId để chỉ định category cha
        subCategory.setCreatedTime(new Date()); // Đặt thời gian tạo

        return categoryRepository.save(subCategory);
    }

    public List<Category> getParentCategories() {
        return categoryRepository.findByParentIdIsNull();
    }

    public List<Category> getSubCategories(String parentId) {
        return categoryRepository.findByParentId(parentId);
    }
}
