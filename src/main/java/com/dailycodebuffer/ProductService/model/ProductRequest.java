package com.dailycodebuffer.ProductService.model;

import lombok.Data;

@Data
public class ProductRequest {
   private String name;
   private double price;
   private long quantity;
}
