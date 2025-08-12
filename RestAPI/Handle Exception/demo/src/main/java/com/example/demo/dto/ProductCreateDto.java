package com.example.demo.dto;

import lombok.Builder;
import jakarta.validation.constraints.*;
@Builder
public record ProductCreateDto(@NotNull @Size(min = 8, max = 8, message = "SKU must be exactly 8 characters") String sku,
                              @NotNull @NotBlank(message = "Product name cannot be empty") String name,
                              @Min(value = 0, message = "Quantity cannot be negative") Integer quantity,
                              @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0") Double price) {
    // This record can be used to create a new Product entity
}