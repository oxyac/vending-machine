package com.oxyac.vendingmachine.data.config;

import com.oxyac.vendingmachine.data.entity.Product;
import com.oxyac.vendingmachine.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        loadProductData();
    }

    private void loadProductData() {
//        if (productRepository.count() == 0) {
//            Product user1 = new Product("Chicken", 9.99, 20 );
//            Product user2 = new Product("Chicken", 9.99, 20  );
//            productRepository.save(user1);
//            productRepository.save(user2);
//        }
//        System.out.println(productRepository.count());
    }
}