package com.simon.wigellpadel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id", unique = true, nullable = false)
    private Customer customer;

    public Address() {}

    public Address(String street, String city, String postalCode, Customer customer) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.customer = customer;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.street).append(", ")
          .append(this.postalCode).append(", ")
          .append(this.city).append(" ");
        return sb.toString();
    }
}