package com.erick.orders.services;

import com.erick.orders.models.Order;
import com.erick.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderrepo;

    @Override
    public Order getOrderById(long id) {
        return orderrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
    }

    @Override
    public List<Order> getByAdvancedAmountGreaterThanZero() {
        List<Order> rtnList = orderrepo.findAllByAdvanceamountGreaterThan(0.0);
        return rtnList;
    }
}
