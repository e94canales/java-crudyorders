package com.erick.orders.services;

import com.erick.orders.models.Order;

import java.util.List;

public interface OrderService {
    Order getOrderById(long id);
    List<Order> getByAdvancedAmountGreaterThanZero();

    // PART 2
    Order postOrder(Order order); // Post
    Order updateOrder(Order order, long id); // Put
    String deleteOrder(long id); // Delete
}
