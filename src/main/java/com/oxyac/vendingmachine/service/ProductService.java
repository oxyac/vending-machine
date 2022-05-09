package com.oxyac.vendingmachine.service;

import com.oxyac.vendingmachine.data.ProductNotFoundException;
import com.oxyac.vendingmachine.data.entity.Product;
import com.oxyac.vendingmachine.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }


    @Override
    public Product saveProduct(Product department) {
        return repository.save(department);
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

    }

    public Product replaceProduct(Product newProduct, Long id) {

        return repository.findById(id).map(product -> {
                    product.setPrice(newProduct.getPrice());
                    product.setName(newProduct.getName());
                    product.setStock(newProduct.getStock());
                    return repository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }
}
