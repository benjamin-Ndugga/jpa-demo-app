package com.example.demo.entities;

import java.io.Serializable;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customer_id;
    private String firstName;
    private String lastName;
    private Character gender;
    private String email;

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
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
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
        return "Customer{" + "customer_id=" + customer_id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + '}';
    }

}
