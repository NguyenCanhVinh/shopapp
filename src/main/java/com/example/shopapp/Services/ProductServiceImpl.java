package com.example.shopapp.Services;

import com.example.shopapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;


  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }
}
