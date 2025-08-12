package com.example.demo.dto;

import lombok.Builder;

@Builder
public record ProductDto(String sku, String name, Integer quantity, Double price) {
    
}
