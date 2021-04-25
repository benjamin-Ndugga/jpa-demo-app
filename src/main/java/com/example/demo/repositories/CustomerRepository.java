package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author benjie_en
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
