package com.example.demo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    @PreAuthorize("hasAuthority('PRODUCT_READ')") // Chỉ user có quyền PRODUCT_READ mới vào được
    public List<String> getAllProducts() {
        return List.of("Product A", "Product B");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public String getProductById(@PathVariable int id) {
        return "Details of Product " + id;
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('PRODUCT_WRITE')") // Yêu cầu quyền PRODUCT_WRITE
    public String createProduct(@RequestBody String product) {
        return "Product '" + product + "' created.";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public String updateProduct(@PathVariable int id, @RequestBody String product) {
        return "Product " + id + " updated to '" + product + "'.";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_DELETE')") // Yêu cầu quyền PRODUCT_DELETE
    public String deleteProduct(@PathVariable int id) {
        return "Product " + id + " deleted.";
    }
    
    @GetMapping("/status")
    // Endpoint này không có @PreAuthorize, nên chỉ cần đăng nhập là vào được
    // vì chúng ta đã cấu hình .anyRequest().authenticated() trong SecurityConfig
    public String getProductStatus() {
        return "All systems are operational.";
    }
}
