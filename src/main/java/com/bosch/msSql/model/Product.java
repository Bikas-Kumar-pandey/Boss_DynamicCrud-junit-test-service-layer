package com.bosch.msSql.model;

import com.bosch.mongo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Entity
@Data
@Table(name = "products_junit")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int productId;
    private String productName;
    private int quantity;
    private double amount;
    private double tax;
    private double totalAmount;

}
