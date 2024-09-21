package com.beecommerce.services;

import com.beecommerce.dto.reponse.CategoryResponse;
import com.beecommerce.dto.request.CategoryRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.models.Category;
import com.beecommerce.models.Product;
import com.beecommerce.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

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


}
