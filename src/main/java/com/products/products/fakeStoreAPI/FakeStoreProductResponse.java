package com.products.products.fakeStoreAPI;


import lombok.Data;

@Data
public class FakeStoreProductResponse {
    String id;
    String title;
    int price;
    String description;
    String category;
    String image;
    Rating rating;
}
