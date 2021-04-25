package com.example.demo;

import com.example.demo.entities.Customer;
import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.Product;
import com.example.demo.repositories.CustomerOrdersRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ProductsRepository;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication(exclude = HazelcastAutoConfiguration.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository cr, CustomerOrdersRepository cor, ProductsRepository pr) {

        return args -> {

            //create some customers
            Customer customer1 = new Customer("Maria", "Kellen", 'F', "maria@gmail.com");
            cr.save(customer1);

            customer1 = new Customer("Bosco", "Mathew", 'M', "bosco@gmail.com");
            cr.save(customer1);

            customer1 = new Customer("Linda", "Ethel", 'F', "linda@gmail.com");
            cr.save(customer1);

            customer1 = new Customer("Richard", "Timothy", 'M', "richard@gmail.com");
            cr.save(customer1);

            customer1 = new Customer("Fred", "Seruwaggi", 'M', "freds@gmail.com");
            cr.save(customer1);

            customer1 = new Customer("Katherine", "Sally", 'F', "Ksally@gmail.com");
            cr.save(customer1);

            //list of customers saved
            //cr.findAll().forEach(System.out::println);
            //lets stock up some products
            Product product = new Product(349376643743L, 1000, "Toothbrush", 10);
            pr.save(product);

            product = new Product(3134437400009993L, 1600, "Matchbox", 100);
            pr.save(product);

            product = new Product(2334100000000123L, 1500, "Milk", 50);
            pr.save(product);

            product = new Product(100232329373743L, 1500, "Eggs", 100);
            pr.save(product);

            product = new Product(200011244844343L, 1500, "Bowls", 1400);
            pr.save(product);

            product = new Product(1000000834343L, 1500, "Salt", 1400);
            pr.save(product);

            product = new Product(10000003342343L, 1000, "Water", 1000);
            pr.save(product);

            //let's get in some orders
            List<Product> all_products = pr.findAll();

            Random random_product_picker = new Random();

            //customers make orders
            cr.findAll().forEach(customer -> {

                Product product_in_cart = all_products.get(random_product_picker.nextInt(all_products.size()));

                //let's assume they all purchase one item
                cor.save(new CustomerOrder(new Date(), customer, product_in_cart, 1));
            });
        };
    }
}
