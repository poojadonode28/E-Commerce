package com.products.products.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateProductRequestDTO {
    private String productName;
    private String category;
    private String imageUrl;
    private int cost;
}
