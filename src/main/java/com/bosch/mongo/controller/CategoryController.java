package com.bosch.mongo.controller;


import com.bosch.mongo.dto.CategoryRequest;
import com.bosch.mongo.dto.CategoryResponse;
import com.bosch.mongo.model.Category;
import com.bosch.mongo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService productService) {
        this.categoryService = productService;
    }

    @PostMapping
    public Category savesCategory(@RequestBody CategoryRequest request) throws Exception {
        return categoryService.savesCategory(request);
    }

    @GetMapping
    public Set<CategoryResponse> getAllCategory() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse getAllCategoryById(@PathVariable int id) {
        return categoryService.getAllCategoryById(id);
    }

}
