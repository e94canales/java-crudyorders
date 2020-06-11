package com.erick.orders.services;

import com.erick.orders.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(long id);
    List<Customer> getByContains(String word);

    // PART 2
    Customer postCustomer(Customer customer); // Post
    Customer updateCustomer(Customer customer, long id); // Patch
    String deleteCustomer(long id); // Delete
}
