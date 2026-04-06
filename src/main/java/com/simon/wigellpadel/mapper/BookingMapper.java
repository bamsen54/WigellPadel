package com.simon.wigellpadel.mapper;

import com.simon.wigellpadel.dto.BookingDto;
import com.simon.wigellpadel.dto.PostBookingDto;
import com.simon.wigellpadel.entity.Booking;
import com.simon.wigellpadel.entity.Court;
import com.simon.wigellpadel.entity.Customer;

public class BookingMapper {

    public static BookingDto toDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        return new BookingDto(
                booking.getId(),
                booking.getBookingReference(),
                booking.getCustomer().getId(),
                booking.getCustomer().getUsername(),
                booking.getCourt().getId(),
                booking.getCourt().getCourtName(),
                booking.getBookingDate(),
                booking.getStartTime(),
                booking.getTotalPriceSek(),
                booking.getTotalPriceEur(),
                booking.getNumberOfPlayers()
        );
    }

    public static Booking fromDto(BookingDto bookingDto) {
        if (bookingDto == null) return null;

        Booking booking = new Booking();
        booking.setId(bookingDto.id());
        booking.setNumberOfPlayers(bookingDto.numberOfPlayers());
        booking.setBookingDate(bookingDto.bookingDate());
        booking.setStartTime(bookingDto.startTime());
        booking.setTotalPriceSek(bookingDto.totalPriceSek());
        booking.setTotalPriceEur(bookingDto.totalPriceEur());

        return booking;
    }

    public static Booking fromPostBookingDto(PostBookingDto dto, Customer customer, Court court) {
        if (dto == null) return null;

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setCourt(court);
        booking.setBookingDate(dto.bookingDate());
        booking.setStartTime(dto.startTime());
        booking.setNumberOfPlayers(dto.numberOfPlayers());

        return booking;
    }
}