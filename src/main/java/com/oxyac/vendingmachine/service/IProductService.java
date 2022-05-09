package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.entity.Product;

import java.util.List;

//contract methods
public interface IProductService {
    List<Product> findAll();
    Product saveProduct(Product d);


    Product getProductById(Long id);

    Product replaceProduct(Product d, Long id);
}
