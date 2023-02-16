package com.bosch.msSql.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private int productId;
    private String productName;
    private double amount;
    private double tax;
    private double totalAmount;
}
