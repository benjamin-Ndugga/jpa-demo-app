package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author benjie_en
 */
public interface ProductsRepository extends JpaRepository<Product, Integer> {

}
