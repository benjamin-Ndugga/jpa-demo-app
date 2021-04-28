package com.example.demo.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author benjie_en
 */
@Entity
@Table(name = "customers",
        uniqueConstraints = {
            @UniqueConstraint(name = "email_id_key", columnNames = "email")
        }
)
public class Customer implements Serializable {

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int isCustomerActive() {
        return customerActive;
    }

    public void setCustomerActive(int customerActive) {
        this.customerActive = customerActive;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private int customerId;
    private String firstName;
    private String lastName;
    private Character gender;
    private String email;

    @Column(name = "customer_active", columnDefinition = "integer default 1")
    private int customerActive;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Character gender, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCustomer_id() {
        return customerId;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customerId = customer_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + '}';
    }

}
