package com.erick.orders.services;

import com.erick.orders.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(long id);
    List<Customer> getByContains(String word);
}
