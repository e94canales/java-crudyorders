package com.erick.orders.services;

import com.erick.orders.models.Customer;
import com.erick.orders.models.Order;
import com.erick.orders.models.Payment;
import com.erick.orders.repositories.CustomerRepository;
import com.erick.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository custrepo;

    @Autowired
    private PaymentRepository payrepo;


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

    @Transactional
    @Override
    public Customer postCustomer(Customer customer) {
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0)
        {
            custrepo.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));

            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        for (Order o : customer.getOrders()){
            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(), o.getOrderdescription(), newCustomer);
            newCustomer.getOrders().add(newOrder);
        }


        return custrepo.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer updateCustomer(Customer customer, long id) {

        Customer currentCustomer = custrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));

        if (customer.getCustname() != null)
        {
            currentCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null)
        {
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.getOpeningamt() != 0)
        {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.getReceiveamt() != 0)
        {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.getPaymentamt() != 0)
        {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.getOutstandingamt() != 0)
        {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if (customer.getPhone() != null){
            currentCustomer.setPhone(customer.getPhone());
        }

        if (customer.getAgent() != null)
        {
           currentCustomer.setAgent(customer.getAgent());
        }

        return custrepo.save(currentCustomer);

    }

    @Transactional
    @Override
    public String deleteCustomer(long id) {
        if (custrepo.findById(id).isPresent()){
            custrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer With Id " + id + " Not Found");
        }
        return "Customer With Id " + id + " has been deleted.";
    }
}
