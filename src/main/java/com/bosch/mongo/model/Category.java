package com.bosch.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Table;

@Document(collection = "category_junit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int categoryId;
    private String category;
    private String description;

    private int productId;

}
