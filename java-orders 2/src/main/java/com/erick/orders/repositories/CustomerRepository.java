package com.erick.orders.repositories;

import com.erick.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findCustomerByCustnameContainsIgnoringCase(String name);
}
