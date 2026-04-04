package com.simon.wigellpadel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "courts")
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "court_name", nullable = false, unique = true)
    private String courtName;

    @Column(name = "price_per_hour_sek")
    private double pricePerHourSek;

    @Column(name = "price_per_hour_eur")
    private double pricePerHourEur;

    @JsonIgnore
    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public Court() {}

    public Court(String courtName, double pricePerHourSek, double pricePerHourEur) {
        this.courtName = courtName;
        this.pricePerHourSek = pricePerHourSek;
        this.pricePerHourEur = pricePerHourEur;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCourtName() { return courtName; }
    public void setCourtName(String courtName) { this.courtName = courtName; }
    public double getPricePerHourSek() { return pricePerHourSek; }
    public void setPricePerHourSek(double pricePerHourSek) { this.pricePerHourSek = pricePerHourSek; }
    public double getPricePerHourEur() { return pricePerHourEur; }
    public void setPricePerHourEur(double pricePerHourEur) { this.pricePerHourEur = pricePerHourEur; }
    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Court{");
        sb.append("id=").append(id);
        sb.append(", courtName='").append(courtName).append('\'');
        sb.append(", pricePerHourSek=").append(pricePerHourSek);
        sb.append(", pricePerHourEur=").append(pricePerHourEur);
        sb.append('}');
        return sb.toString();
    }
}