package com.simon.wigellpadel.controller;

import com.simon.wigellpadel.dto.AddressDto;
import com.simon.wigellpadel.dto.CustomerDto;
import com.simon.wigellpadel.dto.PostCustomerDto;
import com.simon.wigellpadel.entity.Address;
import com.simon.wigellpadel.entity.Customer;
import com.simon.wigellpadel.exception.AddressDoesNotExistException;
import com.simon.wigellpadel.mapper.AddressMapper;
import com.simon.wigellpadel.service.AddressService;
import com.simon.wigellpadel.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    private final AddressService addressService;
    //private final AddressRepository addressRepository;

    private final Logger logger  = LoggerFactory.getLogger(AdminController.class);

    public AdminController(CustomerService customerService,  AddressService addressService) {
        this.customerService = customerService;
        this.addressService  = addressService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomers());
    }

    @PostMapping
    public ResponseEntity<CustomerDto> postCustomer(@Valid @RequestBody PostCustomerDto dto) {
        CustomerDto response = customerService.save(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();


        logger.info("Created user with id " + response.id() + "@" + location);
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Long customerId) {
        customerService.delete(customerId);
        logger.info("Deleted user with id " + customerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    @PostMapping("/{customerId}/addresses")
    public ResponseEntity<CustomerDto> postAddress(@PathVariable Long customerId, @Valid @RequestBody AddressDto dto) {

        Address address = AddressMapper.fromDto(dto);
        Customer customer = customerService.findCustomerEntityById(customerId);

        customer.getAddresses().add(address);
        address.setCustomer(customer);
        addressService.postAddress(address);

        CustomerDto response = customerService.save(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(customerId)
                .toUri();

        logger.info("Added address for customer id: {} @" + location, customerId);

        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long customerId, @PathVariable Long addressId) {

        Customer customer = customerService.findCustomerEntityById(customerId);
        Address address   = customer.getAddresses().stream()
                                    .filter(e -> e.getId()
                                    .equals(addressId))
                                    .findFirst()
                                    .orElseThrow(() -> new AddressDoesNotExistException(
                                        "Customer with id " + customerId + " does not have an address with id " + addressId
                                    ));

        customer.getAddresses().remove(address);
        addressService.deleteAddress(address);

        logger.info("Deleted address with id: {} for customer id: {}", addressId, customerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
