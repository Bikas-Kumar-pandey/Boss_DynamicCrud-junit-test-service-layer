package com.bosch.mongo.service;


import com.bosch.mongo.dto.CategoryRequest;
import com.bosch.mongo.dto.CategoryResponse;
import com.bosch.mongo.model.Category;
import com.bosch.mongo.repository.CategoryRepository;
import com.bosch.msSql.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Service
public class CategoryService {


    private final CategoryRepository categoryRepo;

    private final ProductRepository productRepo;


    public CategoryService(CategoryRepository categoryRepo, ProductRepository productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }


    public Category savesCategory(CategoryRequest request) throws Exception {
        Category category = null;
        if (productRepo.existsById(request.getProductId())) {
            boolean byProductId = categoryRepo.existsByProductId(request.getProductId());
            if (byProductId == false) {
                category = new Category();
                category.setCategoryId(request.getCategoryId());
                category.setProductId(request.getProductId());
                category.setCategory(request.getCategory());
                category.setDescription(request.getDescription());
            } else throw new Exception("Category with given id " + request.getProductId() + " is set");
        } else {
            throw new Exception("No Product with given id");
        }
        return categoryRepo.save(category);
    }

    public Set<CategoryResponse> getAllCategories() {
        List<Category> all = categoryRepo.findAll();
        Set<CategoryResponse> responses = new LinkedHashSet<>();
        for (Category category : all) {
            CategoryResponse response = new CategoryResponse();
            response.setCategory(category.getCategory());
            response.setCategoryId(category.getCategoryId());
            response.setDescription(category.getDescription());
            responses.add(response);
        }
        return responses;
    }

    public CategoryResponse getAllCategoryById(int id) {
        Category category = categoryRepo.findByCategoryId(id);
        CategoryResponse response = new CategoryResponse();
        response.setCategoryId(category.getCategoryId());
        response.setCategory(category.getCategory());
        response.setDescription(category.getDescription());
        return response;
    }
}
