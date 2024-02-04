package com.products.products.controller;

import com.products.products.dto.CreateProductRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/")
    public String getAllProducts(){
        return "all_products";
    }

    @PostMapping("/")
    public String addProducts(@RequestBody CreateProductRequestDTO createProductRequestDTO){
            return createProductRequestDTO.getProductName();
    }

    @GetMapping("/{productId}")
    public Long getProductById(@PathVariable Long productId){
        return productId;
    }

}
