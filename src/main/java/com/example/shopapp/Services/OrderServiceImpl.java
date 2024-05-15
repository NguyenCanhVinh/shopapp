package com.example.shopapp.Services;

import com.example.shopapp.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

  private final OrderRepository orderRepository;


  public OrderServiceImpl( OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }
}
