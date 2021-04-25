package com.example.demo.controllers;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.repositories.CustomerOrdersRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author benjie_en
 */
@RestController
@RequestMapping(value = "/v1/reports")
public class ReportsController {

    private static final Logger LOGGER = Logger.getLogger(ReportsController.class.getName());

    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    @GetMapping("/orders")
    public List<CustomerOrder> getallCustomerOrders() {

        LOGGER.log(Level.INFO, "call to the customer orders repo...");

        List<CustomerOrder> findAll = customerOrdersRepository.findAll();
        LOGGER.log(Level.INFO, findAll.toString());

        return findAll;
    }

    @GetMapping("/orders/{order_id}")
    public CustomerOrder getOrder(@PathVariable("order_id") int order_id) {
        return customerOrdersRepository
                .findById(order_id)
                .orElse(new CustomerOrder());
    }
}
