package com.erick.orders.controllers;

import com.erick.orders.models.Customer;
import com.erick.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> rtnAll = customerService.getAllCustomers();
        return new ResponseEntity<>(rtnAll, HttpStatus.OK);
    }
    // http://localhost:2019/customers/customer/8
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> getById(@PathVariable long id){
        Customer res = customerService.getCustomerById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    // http://localhost:2019/customers/namelike/ea
    @GetMapping(value = "/namelike/{name}", produces = {"application/json"})
    public ResponseEntity<?> getByContainsName(@PathVariable String name){
        List<Customer> rtnList = customerService.getByContains(name);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
}
