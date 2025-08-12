package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    
    @PostMapping("/products")
    public ProductDto createProduct(@Valid @RequestBody ProductCreateDto productCreateDto){
        return productService.save(productCreateDto);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@Valid @PathVariable Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }
}
