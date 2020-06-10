package com.erick.orders.services;

import com.erick.orders.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    Order getOrderById(long id);
    List<Order> getByAdvancedAmountGreaterThanZero();
}
