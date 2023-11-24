package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.entity.Product;
import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductRequest saveProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceOrder(long orderId, long orderQuantity);
}
