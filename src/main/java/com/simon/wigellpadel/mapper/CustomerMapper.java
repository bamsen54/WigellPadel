package com.simon.wigellpadel.mapper;

import com.simon.wigellpadel.dto.CustomerDto;
import com.simon.wigellpadel.dto.CustomerPostDto;
import com.simon.wigellpadel.entity.Customer;

import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer fromPostDto(CustomerPostDto postDto) {
        Customer customer = new Customer();
        customer.setUsername(postDto.username());
        customer.setPassword(postDto.password()); // Kom ihåg att hasha i Service!
        customer.setRole(postDto.role());
        customer.setFirstName(postDto.firstName());
        customer.setLastName(postDto.lastName());
        customer.setAddress(postDto.address());
        return customer;
    }

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getUsername(),
                customer.getRole(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getBookings() != null ?
                        customer.getBookings().stream()
                                .map(BookingMapper::toDto)
                                .collect(Collectors.toList()) : null
        );
    }
}