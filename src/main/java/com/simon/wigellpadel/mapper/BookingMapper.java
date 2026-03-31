package com.simon.wigellpadel.mapper;

import com.simon.wigellpadel.dto.BookingDto;
import com.simon.wigellpadel.entity.Booking;

public class BookingMapper {

    public static BookingDto toDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        return new BookingDto(
                booking.getId(),
                booking.getCourt() != null ? booking.getCourt().getId() : null,
                booking.getCourt() != null ? booking.getCourt().getCourtName() : "Unknown Court",
                booking.getBookingDate(),
                booking.getStartTime(), // Integer 0-23
                booking.getTotalPriceSek(),
                booking.getTotalPriceEur(),
                booking.getNumberOfPlayers()
        );
    }
}