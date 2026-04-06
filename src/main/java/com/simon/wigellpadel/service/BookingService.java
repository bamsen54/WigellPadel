package com.simon.wigellpadel.service;

import com.simon.wigellpadel.dto.BookingDto;
import com.simon.wigellpadel.dto.PostBookingDto;
import com.simon.wigellpadel.entity.Booking;
import com.simon.wigellpadel.entity.Court;
import com.simon.wigellpadel.entity.Customer;
import com.simon.wigellpadel.exception.BookingConflictException;
import com.simon.wigellpadel.exception.CourtDoesNotExistException;
import com.simon.wigellpadel.exception.CustomerDoesNotExistException;
import com.simon.wigellpadel.mapper.BookingMapper;
import com.simon.wigellpadel.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerService customerService;
    private final CourtService courtService;
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Value("${app.exchange.sek-to-eur}")
    private double exchangeSekToEur;

    @Value("${app.exchange.eur-to-sek}")
    private double exchangeEurToSek;

    public BookingService(BookingRepository bookingRepository,  CustomerService customerService, CourtService courtService) {
        this.bookingRepository = bookingRepository;
        this.customerService   = customerService;
        this.courtService      = courtService;
    }

    public List<BookingDto> findByCustomerId(Long customerId) {

        if(customerService.findById(customerId) == null)
            throw new CustomerDoesNotExistException(customerId);

        return bookingRepository.findByCustomerId(customerId).stream()
            .map(BookingMapper::toDto)
            .toList();
    }

    public BookingDto save(PostBookingDto dto) {

        Customer customer = null;
        Court court = null;

        try {
            customer = customerService.findCustomerEntityById(dto.customerId());
            court   = courtService.findCourtEntityById(dto.courtId());
        }

        catch (CustomerDoesNotExistException e) {
            logger.warn("customer with id: {} not found when saving booking - creation attempt rejected",  dto.customerId());
        }

        catch (CourtDoesNotExistException e) {
            logger.warn("court with id: {} not found when saving booking - creation attempt rejected",  dto.courtId());
        }

        if(customer == null) {
            throw new CustomerDoesNotExistException(dto.customerId());
        }

        if(court == null) {
            throw new CourtDoesNotExistException(dto.courtId());
        }

        if (bookingRepository.existsByCourtIdAndBookingDateAndStartTime(dto.courtId(), dto.bookingDate(), dto.startTime())) {
            logger.warn("Court " + dto.courtId() + " is already booked at " + dto.bookingDate() + " " + dto.startTime() + ":00 - creation attempt rejected");
            throw new BookingConflictException("Court " + dto.courtId() + " is already booked at " + dto.bookingDate() + " " + dto.startTime() + ":00");
        }

        Booking booking = BookingMapper.fromPostBookingDto(dto, customer, court);

        double totalPriceSek = court.getPricePerHourSek();
        double totalPriceEur = totalPriceSek * exchangeSekToEur;

        booking.setTotalPriceSek(totalPriceSek);
        booking.setTotalPriceEur(totalPriceEur);

        bookingRepository.save(booking);

        logger.info("booking saved successfully");

        return BookingMapper.toDto(booking);
    }
}
