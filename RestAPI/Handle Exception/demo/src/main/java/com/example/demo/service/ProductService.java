package com.example.demo.service;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.dto.ProductCreateDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductMapper;
import com.example.demo.entity.Product;
import com.example.demo.exception.*;
import com.example.demo.repository.ProductRepository;

import org.springframework.stereotype.Service;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    public ProductDto save(ProductCreateDto productCreateDto){
        Boolean exisProduct = productRepository.existsBySku(productCreateDto.sku());
        if(exisProduct == true){ 
            throw new DuplicateResourceException("SKU '" + productCreateDto.sku() + "' already exists.");
        }
        Product product = productMapper.toEntity(productCreateDto);
        productRepository.save(product);
        return productMapper.toDto(product);
    }     
    public ProductDto getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }   
    public void delete(Long id){
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.deleteById(id);
    }
}
