package com.products.products.service;

import com.products.products.dto.CreateProductRequestDTO;
import com.products.products.fakeStoreAPI.FakeStoreProductResponse;

import java.util.List;

public interface IProductService {

    FakeStoreProductResponse getProductById(Long id);
    FakeStoreProductResponse[] getAllProducts();

    FakeStoreProductResponse patchProductById(Long productId, CreateProductRequestDTO dto);
}
