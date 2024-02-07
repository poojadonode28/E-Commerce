package com.products.products.fakeStoreAPI;

import lombok.Data;

@Data
public class FakeStoreProductRequestDTO {
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
