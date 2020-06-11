package com.erick.orders.controllers;

import com.erick.orders.models.Order;
import com.erick.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    // POST  http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = {"application/json"})
    public ResponseEntity<?> postNewOrder(@Validated @RequestBody Order newOrder)
    {
        newOrder.setOrdnum(0);
        newOrder = orderService.postOrder(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/orders/order/63
    @PutMapping(value = "/order/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateFullOrder(@RequestBody Order newOrder, @PathVariable long id)
    {
        newOrder.setOrdnum(id);
        orderService.updateOrder(newOrder, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // DELETE http://localhost:2019/orders/order/58
    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long id)
    {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
