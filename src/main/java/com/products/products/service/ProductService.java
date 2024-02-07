package com.products.products.service;


import com.products.products.dto.CreateProductRequestDTO;
import com.products.products.fakeStoreAPI.FakeStoreProductRequestDTO;
import com.products.products.fakeStoreAPI.FakeStoreProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService implements IProductService{

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    @Override
    public FakeStoreProductResponse getProductById(Long productId) {
        RestTemplate template = restTemplateBuilder.build();
        FakeStoreProductResponse dto =template.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductResponse.class,productId).getBody();
        return dto;
    }

    @Override
    public FakeStoreProductResponse[] getAllProducts() {
        FakeStoreProductResponse[] data = restTemplateBuilder.build().getForEntity("https://fakestoreapi.com/products",FakeStoreProductResponse[].class).getBody();
        return data;

    }

    @Override
    public FakeStoreProductResponse patchProductById(Long productId, CreateProductRequestDTO dto) {
        FakeStoreProductRequestDTO response = new FakeStoreProductRequestDTO();
        response.setCategory(dto.getCategory());
        response.setImage(dto.getImageUrl());
        response.setPrice(dto.getCost());
        response.setTitle(dto.getProductName());
         ResponseEntity<FakeStoreProductResponse> data = requestForEntity(HttpMethod.PATCH,"https://fakestoreapi.com/products/{productId}",response, FakeStoreProductResponse.class,productId);
         return data.getBody();
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate t = restTemplateBuilder.build();
        RequestCallback requestCallback = t.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = t.responseEntityExtractor(responseType);
        return t.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }


}
