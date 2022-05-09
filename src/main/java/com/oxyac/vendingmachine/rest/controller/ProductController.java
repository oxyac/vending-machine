package com.oxyac.vendingmachine.rest.controller;

import com.oxyac.vendingmachine.data.entity.Product;
import com.oxyac.vendingmachine.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService departmentService;

    @GetMapping("/products")
    private List<Product> getProducts(){

        log.info("incoming get");
        return departmentService.findAll();
    }

    @PostMapping("/products")
    private Product newProduct(@RequestBody Product newProduct) {

        return departmentService.saveProduct(newProduct);
    }

    @GetMapping("/products/{id}")
    private Product getProductById(@PathVariable Long id) {

        return departmentService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    private Product replaceProduct(@RequestBody Product department, @PathVariable Long id) {

        return departmentService.replaceProduct(department, id);
    }

    @DeleteMapping("/products/{id}")
    private void deleteProduct(@PathVariable Long id) {

        departmentService.deleteProductById(id);
    }
}
