package com.simon.wigellpadel.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "booking_reference", unique = true, nullable = false, updatable = false)
    private final String bookingReference = UUID.randomUUID().toString().substring(0, 18);

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "court_id", nullable = false)
    private Court court;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "start_time", nullable = false)
    private Integer startTime;

    @Column(name = "total_price_sek")
    private double totalPriceSek;

    @Column(name = "total_price_eur")
    private double totalPriceEur;

    @Column(name = "num_players")
    private int numberOfPlayers;

    public Booking() {}

    public Booking(Customer customer, Court court, LocalDate bookingDate, Integer startTime, int numberOfPlayers) {
        this.customer = customer;
        this.court = court;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.numberOfPlayers = numberOfPlayers;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBookingReference() { return this.bookingReference; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Court getCourt() { return court; }
    public void setCourt(Court court) { this.court = court; }
    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    public Integer getStartTime() { return startTime; }
    public void setStartTime(Integer startTime) { this.startTime = startTime; }
    public double getTotalPriceSek() { return totalPriceSek; }
    public void setTotalPriceSek(double totalPriceSek) { this.totalPriceSek = totalPriceSek; }
    public double getTotalPriceEur() { return totalPriceEur; }
    public void setTotalPriceEur(double totalPriceEur) { this.totalPriceEur = totalPriceEur; }
    public int getNumberOfPlayers() { return numberOfPlayers; }
    public void setNumberOfPlayers(int numberOfPlayers) { this.numberOfPlayers = numberOfPlayers; }
}