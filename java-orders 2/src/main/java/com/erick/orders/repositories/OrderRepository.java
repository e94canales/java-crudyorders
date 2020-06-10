package com.erick.orders.repositories;

import com.erick.orders.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByAdvanceamountGreaterThan(double num);
}
