package com.example.shopapp.controller;

import com.example.shopapp.dto.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order_details")
public class OrderDetaiController {

    @PostMapping("")
    public ResponseEntity<?> createrOrderDetails(
            @Valid @RequestBody OrderDetailDTO orderDetailDTO
            ){
        return ResponseEntity.ok("creater Order_details here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(
            @Valid @PathVariable("id") Long id){
        return ResponseEntity.ok("getOrderDetail with id"+ id);
    }

    //lay ra danh sach orderDetail cua mot order nao do
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("orderId") Long orderId){
        return ResponseEntity.ok("get_order_Detail with orderid"+ orderId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable("id") Long id,
            @RequestBody OrderDetailDTO newOrderDetailDTO
    ){
        return ResponseEntity.ok("updateOrderDetail with id"+id+ ",newOrderDetail"+ newOrderDetailDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(@Valid @PathVariable("id") Long id){
        return ResponseEntity.noContent().build();
    }
}
