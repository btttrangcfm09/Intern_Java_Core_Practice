package com.example.demo.repository;

import org.springframework.data.repository.ListCrudRepository;
import com.example.demo.entity.*;
public interface ProductRepository extends ListCrudRepository<Product, Long> {
    Boolean existsBySku(String sku);
}
