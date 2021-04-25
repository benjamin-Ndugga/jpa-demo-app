package com.example.demo.controllers;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author benjie_en
 */
@RestController
@RequestMapping(value = "/v1/customers")
public class CustomerController {

    private static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> fetchAllCustomers() {
        LOGGER.log(Level.INFO, "calling the fetch function...");
        return customerRepository.findAll();
    }

    @GetMapping(path = "/{customer_id}")
    @Cacheable("customers")
    public Customer fetchCustomerInfo(@PathVariable("customer_id") int customer_id) {
        try {
            //simulated a slow response
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return customerRepository.findById(customer_id)
                .orElse(new Customer());
    }

  
    @PostMapping("/register")
    public Customer registerNewCustomer(@RequestBody Customer customer) {
        LOGGER.log(Level.INFO, customer.toString());
        return customerRepository.save(customer);
    }

    @PutMapping("/update/{customer_id}")
    public Customer updateCustomer(@RequestBody Customer Customer, @PathVariable("customer_id") int customer_id) {

        //fetchAllCustomers()
        if (customerRepository.findById(customer_id).orElse(null) != null) {
            customerRepository.deleteById(customer_id);
        }

        return customerRepository.save(Customer);
    }

}
