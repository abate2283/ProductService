package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.entity.Product;
import com.dailycodebuffer.ProductService.exception.ProductServiceException;
import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product...");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice()).build();
        productRepository.save(product);
        log.info("Product created...");
        return product.getProductId();
    }

    @Override
    public ProductRequest saveProduct(ProductRequest productRequest) {
        log.info("Adding product...");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity()).build();
        productRepository.save(product);
        log.info("Product created...");
        return productRequest;
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get product for product with id: {}" + productId);
        Product product = productRepository.findById(productId).orElseThrow(()->new ProductServiceException("Product with given productId NOT found!", "PRODUCT_ID NOT FOUND! TRY AGAIN!!!"));

        ProductResponse productResponse
                = new ProductResponse();
        copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public void reduceOrder(long orderId, long orderQuantity) {
        Product product = productRepository.findById(orderId).orElseThrow(()->new ProductServiceException("product with given id not found", "PRODUCT_NOT_FOUND"));
        if(product.getQuantity()< orderQuantity){
            throw new ProductServiceException("Product does not have sufficient Quantity", "INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity()-orderQuantity);
        productRepository.save(product);
        log.info("Product Quantity updated Successfully");

    }
}
