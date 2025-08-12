package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 8)
    @NotNull
    @Size(min = 8, max = 8)
    private String sku;
    @Column(nullable = false)
    @NotNull
    @NotBlank
    private String name;
    @Column(nullable = false)
    @Min(0)
    private Integer quantity;
    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;
    private Boolean isActive;

}