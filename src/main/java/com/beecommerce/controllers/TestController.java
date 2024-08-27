package com.beecommerce.controllers;

import com.beecommerce.models.Category;
import com.beecommerce.repositories.CategoryRepository;
import com.beecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/heartbeat")
    public String heartbeat() {
        return "OK";
    }

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String getAll() {
        Optional<Category> c = categoryRepository.findAll().stream().findFirst();
        return c.toString();
    }
}
