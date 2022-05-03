package com.oxyac.vendingmachine.data.repository;

import com.oxyac.vendingmachine.data.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

//    List<Department> findByProjectName(String project_name);
}


