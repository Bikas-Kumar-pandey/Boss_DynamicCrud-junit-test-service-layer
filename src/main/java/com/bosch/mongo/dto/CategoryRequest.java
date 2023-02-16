package com.bosch.mongo.dto;

import lombok.Data;

@Data
public class CategoryRequest {

    private int categoryId;
    private int productId;
    private String category;
    private String description;
}
