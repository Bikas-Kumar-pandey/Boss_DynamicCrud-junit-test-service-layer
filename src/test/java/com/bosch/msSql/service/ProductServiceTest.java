package com.bosch.msSql.service;

import com.bosch.mongo.model.Category;
import com.bosch.mongo.repository.CategoryRepository;
import com.bosch.msSql.dto.ProductDetails;
import com.bosch.msSql.dto.ProductRequest;
import com.bosch.msSql.model.Product;
import com.bosch.msSql.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {


    ProductRequest productRequest;
    Product products;
    ProductDetails productDetails;
    Category category;
    @InjectMocks
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private CategoryRepository categoryRepo;
    private int productId;

    @BeforeEach
    public void setUp() {
        productService = new ProductService(productRepository, categoryRepo);
        products = new Product(1, "Nike", 1, 999.99, 10.0, 1.0);
        productDetails = new ProductDetails(1, "Nike", 1, 999.99, 10.0, 1.0, 1, "footwear", "All footwear are here");
        category = new Category(1, "footwear", "Shoes", 1);
    }

    @Test
    void saveProducts() throws Exception {
        when(productRepository.save(any(Product.class))).thenReturn(products);
        productRequest = new ProductRequest("Nike", 999.99, 10.0, 1);
        Product product = productService.saveProducts(productRequest);
        Assertions.assertThat(product.getProductName()).isEqualTo(productRequest.getProductName());
        Assertions.assertThat(product.getAmount()).isEqualTo(productRequest.getAmount());
        Assertions.assertThat(product.getTax()).isEqualTo(productRequest.getTax());
        Assertions.assertThat(product.getQuantity()).isEqualTo(productRequest.getQuantity());
    }

    //Exception Checking By Id
    @Test
    void getProductDetialsExceptions() throws Exception {
        when(categoryRepo.findById(anyInt())).thenReturn(Optional.empty());//Only Return Empty
        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(products));
        Assertions.assertThatThrownBy(() -> productService.getProductDetial(anyInt())).isInstanceOf(Exception.class)
                .hasMessage("Product with given id doesn't exists.");
    }

//Check Product and Category by id.
    @Test
    void getProductById() throws Exception {
        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(products));
        when(categoryRepo.findByProductId(anyInt())).thenReturn(category);
        when(productRepository.existsById(anyInt())).thenReturn(true);

        ProductDetails productDetial = productService.getProductDetial(anyInt());

        Assertions.assertThat(products.getProductName()).isEqualTo(productDetial.getProductName());
        Assertions.assertThat(productDetial).isNotNull();
        Assertions.assertThat(category.getCategory()).isEqualTo(productDetial.getCategory());
    }

//GET ALL PRODUCT'S
    @Test
    void getAllProductDetails() {
        List<ProductDetails> productDetailsList = new ArrayList<>();

        ProductDetails productDetailss = new ProductDetails(1, "Nike", 1, 999.99, 10.0, 1.0, 1, "footwear", "All footwear are here");
        productDetailsList.add(productDetailss);
        for (ProductDetails productDetails1 :productDetailsList){
            System.out.println(productDetails1);

        }
        when(productRepository.findAll()).thenReturn((List<Product>) productDetailss);
        //---------------------------
        Set<ProductDetails> actualProductDetails = productService.getAllProductDetails();
        for (ProductDetails value:actualProductDetails){
            Assertions.assertThat(value.getProductName()).isEqualTo(productDetails.getProductName());
        }


    }
}