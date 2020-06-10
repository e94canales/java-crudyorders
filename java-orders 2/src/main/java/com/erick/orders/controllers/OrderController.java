package com.erick.orders.controllers;

import com.erick.orders.models.Order;
import com.erick.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> getOrderById(@PathVariable long id){
        Order res = orderService.getOrderById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    // http://localhost:2019/orders/advanceamount
    @GetMapping(value = "/advanceamount", produces = {"application/json"})
    public ResponseEntity<?> getByAdvanceAmount(){
        List<Order> rtnList = orderService.getByAdvancedAmountGreaterThanZero();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

}
