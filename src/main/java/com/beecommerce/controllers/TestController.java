package com.beecommerce.controllers;

import com.beecommerce.models.Category;
import com.beecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller("api/v1")
public class TestController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String getCategories(@RequestBody String name) {
        Optional<Category> c = categoryService.getCategoryByName(name);
        return c.toString();
    }
}
