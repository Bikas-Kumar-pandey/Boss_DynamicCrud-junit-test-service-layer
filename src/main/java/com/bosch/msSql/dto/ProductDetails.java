package com.bosch.msSql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
    private int productId;

    private String productName;
    private int quantity;
    private double amount;
    private double tax;
    private double totalAmount;

    private int categoryId;
    private String category;
    private String description;



}
