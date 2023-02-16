package com.bosch.msSql.service;

import com.bosch.mongo.model.Category;
import com.bosch.mongo.repository.CategoryRepository;
import com.bosch.msSql.dto.ProductDetails;
import com.bosch.msSql.dto.ProductRequest;
import com.bosch.msSql.model.Product;
import com.bosch.msSql.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public ProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public Product saveProducts(ProductRequest request) throws Exception {

        if (request.getQuantity() == 0) {
            throw new Exception("please Enter valid Quantity");
        }

        if (request.getTax() > 100) {
            throw new Exception("Tax cannot be more than 100%");
        }
        Product product = new Product();
//CALCULATE TAX AS PERCENTAGE
        double amount = request.getAmount();
        double tax = request.getTax();
        double taxs = amount / tax;
        product.setTotalAmount(amount + taxs);
// ASSIGN TO  ENTITY
        product.setProductName(request.getProductName());
        product.setQuantity(request.getQuantity());
        product.setAmount(amount);
        product.setTax(tax);
        return productRepo.save(product);
    }


    public ProductDetails getProductDetial(int id) throws Exception {
        ProductDetails details = new ProductDetails();
        try {
            if (!productRepo.existsById(id)) {
                throw new Exception("Product with given id doesn't exists.");
            }
            Optional<Product> products = productRepo.findById(id);
            Product product = products.get();
            Category category = categoryRepo.findByProductId(id);

            details.setProductId(product.getProductId());
            details.setProductName(product.getProductName());
            details.setAmount(product.getAmount());
            details.setQuantity(product.getQuantity());
            details.setTax(product.getTax());
            details.setTotalAmount(product.getTotalAmount());

            details.setCategory(category.getCategory());
            details.setDescription(category.getDescription());
            details.setCategoryId(category.getCategoryId());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public Set<ProductDetails> getAllProductDetails() {
        List<Product> all = productRepo.findAll();
        List<Category> all1 = categoryRepo.findAll();
        Set<ProductDetails> productDetailsSet = new LinkedHashSet<>();
        for (Product products : all) {
            ProductDetails details = new ProductDetails();
            int productId = products.getProductId();
            details.setProductId(productId);
            details.setProductName(products.getProductName());
            details.setTax(products.getTax());
            details.setQuantity(products.getQuantity());
            details.setAmount(products.getAmount());
            details.setTotalAmount(products.getTotalAmount());
            for (Category category : all1) {
                details.setCategory(category.getCategory());
                details.setDescription(category.getDescription());
                productDetailsSet.add(details);
            }
        }
        return productDetailsSet;
    }
}
