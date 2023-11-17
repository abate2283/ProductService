package com.dailycodebuffer.ProductService.controller;

import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);

        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable long productId){
        ProductResponse productResponse = productService.getProductById(productId);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }

//    @PostMapping
    public ResponseEntity<ProductRequest> saveProduct(@RequestBody ProductRequest productRequest){
        var product = productService.saveProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
