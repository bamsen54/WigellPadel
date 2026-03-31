package com.simon.wigellpadel.controller;

import com.simon.wigellpadel.dto.AddressDto;
import com.simon.wigellpadel.dto.CustomerDto;
import com.simon.wigellpadel.dto.PostCustomerDto;
import com.simon.wigellpadel.entity.Address;
import com.simon.wigellpadel.entity.Customer;
import com.simon.wigellpadel.mapper.AddressMapper;
import com.simon.wigellpadel.mapper.CustomerMapper;
import com.simon.wigellpadel.repository.AddressRepository;
import com.simon.wigellpadel.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class AdminController {

    private final CustomerService customerService;
    private final AddressRepository addressRepository;
    private final Logger logger  = LoggerFactory.getLogger(AdminController.class);

    public AdminController(CustomerService customerService,  AddressRepository addressRepository) {
        this.customerService   = customerService;
        this.addressRepository = addressRepository;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> postCustomer(@Valid @RequestBody PostCustomerDto dto) {
        CustomerDto response = customerService.save(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @Transactional
    @PostMapping("/{customerId}/addresses")
    public ResponseEntity<CustomerDto> postAddress(@PathVariable Long customerId, @Valid @RequestBody AddressDto dto) {

        Address address = AddressMapper.fromDto(dto);
        Customer customer = customerService.findCustomerEntityById(customerId);

        if(customer.getAddresses().isEmpty()) {
            customer.getAddresses().add(address);
            address.setCustomer(customer);
            addressRepository.save(address);
            logger.info("Added address for customer id: {}", customerId);
        }

        CustomerDto response = customerService.save(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/../customers/{customerId}")
                .buildAndExpand(customerId)
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}
