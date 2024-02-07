package com.products.products.controller;

import com.products.products.dto.CreateProductRequestDTO;
import com.products.products.fakeStoreAPI.FakeStoreProductResponse;
import com.products.products.service.IProductService;
import com.products.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    @Autowired
    public ProductController(IProductService productService){
        this.productService=productService;
    }

    @GetMapping("/")
    public FakeStoreProductResponse[] getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public String addProducts(@RequestBody CreateProductRequestDTO createProductRequestDTO){
            return createProductRequestDTO.getProductName();
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProductById(@PathVariable Long productId){
        FakeStoreProductResponse data = productService.getProductById(productId);
        if(Objects.isNull(data)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<FakeStoreProductResponse> patchProductById(@PathVariable Long productId,@RequestBody CreateProductRequestDTO createProductRequestDTO){
        FakeStoreProductResponse response=productService.patchProductById(productId,createProductRequestDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

}
