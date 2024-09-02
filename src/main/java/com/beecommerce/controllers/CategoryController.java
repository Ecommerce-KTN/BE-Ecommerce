package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.CategoryResponse;
import com.beecommerce.dto.request.CategoryRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse createdCategory = categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok(createdCategory);
    }
//    create child Category của  cha có id
    @PostMapping("/parent/{parentId}")
    public ResponseEntity<CategoryResponse> createChildCategory(@PathVariable String parentId, @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse createdCategory = categoryService.createChildCategory(parentId, categoryRequest);
        return ResponseEntity.ok(createdCategory);
    }





    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryDetails) {
        CategoryResponse updatedCategory = categoryService.updateCategory(id, categoryDetails)
                .orElseThrow(() -> new Exception(ErrorCode.CATEGORY_NOT_FOUND));
        return ResponseEntity.ok(updatedCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable String id) {
        CategoryResponse category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new Exception(ErrorCode.CATEGORY_NOT_FOUND));
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/parents")
    public ResponseEntity<List<CategoryResponse>> getParentCategories() {
        List<CategoryResponse> parentCategories = categoryService.getParentCategories();
        if (parentCategories.isEmpty()) {
            throw new Exception(ErrorCode.CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok(parentCategories);
    }

    @GetMapping("/by-parent/{parentId}")
    public ResponseEntity<List<CategoryResponse>> getCategoriesByParentId(@PathVariable String parentId) {
        List<CategoryResponse> categories = categoryService.getCategoriesByParentId(parentId);
        if (categories.isEmpty()) {
            throw new Exception(ErrorCode.CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok(categories);
    }

}
