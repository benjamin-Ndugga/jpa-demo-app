package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/v1/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping
    public List<Product> fetchAllProducts() {
        return productsRepository.findAll();
    }
    
    @GetMapping(path = "/{product_id}")
    public Product fetchProduct(@PathVariable("product_id") int product_id) {
        return productsRepository.findById(product_id)
                .orElse(new Product());
    }

    @PutMapping("/update/{product_id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("product_id") int product_id) {
        //fetchAllCustomers()
        if (productsRepository.findById(product_id).orElse(null) != null) {
            productsRepository.deleteById(product_id);
        }
        return productsRepository.save(product);
    }

    @PostMapping("/register")
    public Product registerProduct(@RequestBody Product product) {
        return productsRepository.save(product);
    }

}
