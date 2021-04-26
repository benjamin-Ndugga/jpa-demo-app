package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author benjie_en
 */
@Entity
@Table(name = "customer_orders")
public class CustomerOrder implements Serializable {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "orders_seq_generator",
            allocationSize = 1,
            initialValue = 1)
    private int order_id;
    @Column(name = "orderDate", nullable = false)
    private Date orderDate;

 
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customer_id", foreignKey = @ForeignKey(name = "customer_id_fk"))
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", foreignKey = @ForeignKey(name = "product_id_fk"))
    private Product product;

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CustomerOrder() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerOrder(Date orderDate, Customer customer, Product product, int quantity) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" + "order_id=" + order_id + ", orderDate=" + orderDate + ", customer=" + customer + ", product=" + product + '}';
    }

}
