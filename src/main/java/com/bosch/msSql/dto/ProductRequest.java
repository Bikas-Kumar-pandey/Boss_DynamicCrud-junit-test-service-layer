package com.bosch.msSql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {
    private String productName;
    private double amount;
    private double tax;
    private int quantity;


}
