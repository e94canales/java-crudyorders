package com.erick.orders.services;

import com.erick.orders.models.Customer;
import com.erick.orders.models.Order;
import com.erick.orders.models.Payment;
import com.erick.orders.repositories.OrderRepository;
import com.erick.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderrepo;

    @Autowired
    private PaymentRepository payrepo;

    @Override
    public Order getOrderById(long id) {
        return orderrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
    }

    @Override
    public List<Order> getByAdvancedAmountGreaterThanZero() {
        List<Order> rtnList = orderrepo.findAllByAdvanceamountGreaterThan(0.0);
        return rtnList;
    }

    @Transactional
    @Override
    public Order postOrder(Order order) {
        Order newOrder = new Order();

        if (order.getOrdnum() != 0)
        {
            orderrepo.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));

            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(order.getCustomer());

        for (Payment p : order.getPayments()){
            Payment newPay = payrepo.findById(p.getPaymentid()).orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
            newOrder.addPayments(newPay);
        }

        return orderrepo.save(newOrder);
    }

    @Transactional
    @Override
    public Order updateOrder(Order order, long id) {
        Order currentOrder = orderrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));

        if (order.getOrdamount() > 0.0)
        {
            currentOrder.setOrdamount(order.getOrdamount());
        }

        if (order.getAdvanceamount() > 0.0)
        {
            currentOrder.setAdvanceamount(order.getAdvanceamount());
        }

        if (order.getOrderdescription() != null)
        {
            currentOrder.setOrderdescription(order.getOrderdescription());
        }

        if (order.getCustomer() != null)
        {
            currentOrder.setCustomer(order.getCustomer());
        }

        if (order.getPayments().size() > 0)
        {
            for (Payment p : order.getPayments())
            {
                Payment newPay = payrepo.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
                currentOrder.addPayments(newPay);
            }
        }

        return orderrepo.save(currentOrder);
    }

    @Transactional
    @Override
    public String deleteOrder(long id) {
        if (orderrepo.findById(id).isPresent()){
            orderrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Order With Id " + id + " Not Found");
        }
        return "Customer With Id " + id + " has been deleted.";
    }
}
