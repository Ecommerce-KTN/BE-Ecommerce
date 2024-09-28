package com.beecommerce.controllers;

import com.beecommerce.dto.response.ApiResponse;
import com.beecommerce.dto.response.CategoryResponse;
import com.beecommerce.dto.request.CategoryRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.models.Category;
import com.beecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.beecommerce.mapper.CategoryMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        try {
            Category category = categoryService.createCategory(CategoryMapper.INSTANCE.convertToEntity(request));
            return ResponseEntity.ok(CategoryMapper.INSTANCE.convertToResponse(category));
        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.CATEGORY_ALREADY_EXISTS.getStatusCode())
                    .body(CategoryMapper.INSTANCE.convertToResponse(Category.builder().build()));
        }
    }


    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            if (categories.isEmpty()) {
                return ResponseEntity.status(ErrorCode.CATEGORY_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.CATEGORY_NOT_FOUND.getMessage())
                                .build());
            }

            List<CategoryResponse> categoryResponses = categories.stream()
                    .map(CategoryMapper.INSTANCE::convertToResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .data(categoryResponses)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.DATABASE_ERROR.getMessage())
                            .build());
        }
    }

    @GetMapping("/10")
    public ResponseEntity<ApiResponse> get10Categories() {
        try {
            List<Category> categories = categoryService.get10Categories();
            if (categories.isEmpty()) {
                return ResponseEntity.status(ErrorCode.CATEGORY_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.CATEGORY_NOT_FOUND.getMessage())
                                .build());
            }

            List<CategoryResponse> categoryResponses = categories.stream()
                    .map(CategoryMapper.INSTANCE::convertToResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .data(categoryResponses)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.DATABASE_ERROR.getMessage())
                            .build());
        }
    }

    @GetMapping("/parents")
    public ResponseEntity<ApiResponse> getParentCategories() {
        try {
            List<Category> categories = categoryService.getParentCategories();
            if (categories.isEmpty()) {
                return ResponseEntity.status(ErrorCode.CATEGORY_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.CATEGORY_NOT_FOUND.getMessage())
                                .build());
            }

            List<CategoryResponse> categoryResponses = categories.stream()
                    .map(CategoryMapper.INSTANCE::convertToResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .data(categoryResponses)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.DATABASE_ERROR.getMessage())
                            .build());
        }
    }

    @GetMapping("/subcategories/{parentId}")
    public ResponseEntity<ApiResponse> getSubCategories(@PathVariable String parentId) {
        try {
            List<Category> categories = categoryService.getSubCategories(parentId);
            if (categories.isEmpty()) {
                return ResponseEntity.status(ErrorCode.CATEGORY_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.CATEGORY_NOT_FOUND.getMessage())
                                .build());
            }

            List<CategoryResponse> categoryResponses = categories.stream()
                    .map(CategoryMapper.INSTANCE::convertToResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .data(categoryResponses)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.DATABASE_ERROR.getMessage())
                            .build());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable String id) {
        try {
            Category category = categoryService.getCategoryById(id);
            if (category == null) {
                return ResponseEntity.status(ErrorCode.CATEGORY_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.CATEGORY_NOT_FOUND.getMessage())
                                .build());
            }
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Category retrieved successfully")
                    .data(CategoryMapper.INSTANCE.convertToResponse(category))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryRequest) {
        try {
            Optional<Category> updatedCategory = categoryService.updateCategory(id, categoryRequest);

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Category updated successfully")
                    .data(CategoryMapper.INSTANCE.convertToResponse(updatedCategory.get()))
                    .build());

        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PostMapping("/parent/{parentCategoryId}")
    public ResponseEntity<Category> createSubCategory(@PathVariable String parentCategoryId, @RequestBody CategoryRequest categoryRequest) {
        Category subCategory = categoryService.createSubCategory(parentCategoryId, categoryRequest);
        return new ResponseEntity<>(subCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String id) {
        try {
            Category category = categoryService.getCategoryById(id);
            if (category == null) {
                return ResponseEntity.status(ErrorCode.CATEGORY_NOT_FOUND.getStatusCode())
                        .body(ApiResponse.builder()
                                .success(false)
                                .message(ErrorCode.CATEGORY_NOT_FOUND.getMessage())
                                .build());
            }

            categoryService.deleteCategory(id);

            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.status(ErrorCode.DATABASE_ERROR.getStatusCode())
                    .body(ApiResponse.builder()
                            .success(false)
                            .message(ErrorCode.DATABASE_ERROR.getMessage())
                            .build());
        }
    }
}
