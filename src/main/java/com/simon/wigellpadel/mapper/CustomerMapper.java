package com.simon.wigellpadel.mapper;

import com.simon.wigellpadel.dto.AddressDto;
import com.simon.wigellpadel.dto.BookingDto;
import com.simon.wigellpadel.dto.CustomerDto;
import com.simon.wigellpadel.dto.PostCustomerDto;
import com.simon.wigellpadel.entity.Address;
import com.simon.wigellpadel.entity.Booking;
import com.simon.wigellpadel.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public static CustomerDto toDto(Customer customer) {
        if (customer == null) return null;

        List<AddressDto> addressDtos = customer.getAddresses() != null ?
                customer.getAddresses().stream()
                        .map(AddressMapper::toDto)
                        .collect(Collectors.toList())
                : new ArrayList<>();

        List<BookingDto> bookingDtos = customer.getBookings() != null ?
                customer.getBookings().stream()
                        .map(BookingMapper::toDto)
                        .collect(Collectors.toList())
                : new ArrayList<>();

        return new CustomerDto(
                customer.getId(),
                customer.getUsername(),
                customer.getRole(),
                customer.getFirstName(),
                customer.getLastName(),
                addressDtos,
                bookingDtos
        );
    }

    public static Customer fromDto(CustomerDto customerDto) {
        if (customerDto == null) return null;

        Customer customer = new Customer();
        customer.setId(customerDto.id());
        customer.setUsername(customerDto.username());
        customer.setFirstName(customerDto.firstName());
        customer.setLastName(customerDto.lastName());

        if (customerDto.addresses() != null && !customerDto.addresses().isEmpty()) {
            Address address = AddressMapper.fromDto(customerDto.addresses().get(0));
            address.setCustomer(customer);
            customer.setAddresses(List.of(address));
        } else {
            customer.setAddresses(new ArrayList<>());
        }

        if (customerDto.bookings() != null && !customerDto.bookings().isEmpty()) {
            List<Booking> bookings = customerDto.bookings().stream()
                    .map(BookingMapper::fromDto)
                    .collect(Collectors.toList());
            bookings.forEach(booking -> booking.setCustomer(customer));
            customer.setBookings(bookings);
        } else {
            customer.setBookings(new ArrayList<>());
        }

        return customer;
    }

    public static Customer fromPostCustomerDtoToCustomer(PostCustomerDto dto) {
        if (dto == null) return null;

        Customer customer = new Customer();

        customer.setUsername(dto.username());
        customer.setPassword(dto.password());
        customer.setRole(dto.role());
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());

        customer.setAddresses(new ArrayList<>());
        customer.setBookings(new ArrayList<>());

        return customer;
    }
}