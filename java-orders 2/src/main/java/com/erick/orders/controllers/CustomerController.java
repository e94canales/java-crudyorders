package com.erick.orders.controllers;

import com.erick.orders.models.Customer;
import com.erick.orders.services.CustomerService;
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

    // POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = {"application/json"})
    public ResponseEntity<?> postNewCustomer(@Validated @RequestBody Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customerService.postCustomer(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        // http://localhost:2019/customers/customer/id
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/customers/customer/id
    @PutMapping(value = "/customer/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateFullCustomer(@Validated @RequestBody Customer updateCustomer, @PathVariable long id)
    {
        updateCustomer.setCustcode(id);
        customerService.postCustomer(updateCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // PATCH http://localhost:2019/customers/customer/id
    @PatchMapping(value = "/customer/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customer updateCustomer, @PathVariable long id)
    {
        customerService.updateCustomer(updateCustomer, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/customers/customer/54
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long id)
    {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
