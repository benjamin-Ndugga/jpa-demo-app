package com.example.demo.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author benjie_en
 */
@Entity
@Table(name = "Products", uniqueConstraints = {
    @UniqueConstraint(name = "product_barcode_number_key", columnNames = "product_barcode_number")
})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_id_generator", allocationSize = 1, initialValue = 100)
    @Column(name = "product_id", nullable = false)
    private int product_id;
    @Column(name = "product_barcode_number", nullable = false)
    private long barCodeNumber;
    @Column(name = "product_price", nullable = false)
    private int price;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_quantity", nullable = false)
    private int quantity;

    public Product() {
    }

    public Product(long barCodeNumber, int price, String productName, int quantity) {
        this.barCodeNumber = barCodeNumber;
        this.price = price;
        this.productName = productName;
        this.quantity = quantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public long getBarCodeNumber() {
        return barCodeNumber;
    }

    public void setBarCodeNumber(long barCodeNumber) {
        this.barCodeNumber = barCodeNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", barCodeNumber=" + barCodeNumber + ", price=" + price + ", productName=" + productName + ", quantity=" + quantity + '}';
    }

}//end of class
