package com.bosch.msSql.controller;

import com.bosch.msSql.dto.ProductDetails;
import com.bosch.msSql.dto.ProductRequest;
import com.bosch.msSql.dto.ProductResponse;
import com.bosch.msSql.model.Product;
import com.bosch.msSql.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product saveProducts(@RequestBody ProductRequest request) throws Exception {
        return productService.saveProducts(request);
    }
@GetMapping("/{id}")
    public ProductDetails getProductDetails(@PathVariable int id) throws Exception {
        return productService.getProductDetial(id);
}
    @GetMapping("product")
    public Set<ProductDetails> getAllProductDetails() throws Exception {
      return   productService.getAllProductDetails();
    }


//@PostMapping
//    public void purchaseProduct(){
//
//}

}
