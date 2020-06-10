package com.erick.orders.services;

import com.erick.orders.models.Customer;
import com.erick.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository custrepo;

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> rtnList = new ArrayList<>();
        custrepo.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Customer getCustomerById(long id) {
        return custrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
    }

    @Override
    public List<Customer> getByContains(String name) {
        return custrepo.findCustomerByCustnameContainsIgnoringCase(name);
    }
}
