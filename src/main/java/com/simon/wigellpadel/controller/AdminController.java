package com.simon.wigellpadel.controller;

import com.simon.wigellpadel.dto.CustomerDto;
import com.simon.wigellpadel.dto.CustomerPostDto;
import com.simon.wigellpadel.entity.Customer;
import com.simon.wigellpadel.exception.UsernameNotAvailableException;
import com.simon.wigellpadel.mapper.CustomerMapper;
import com.simon.wigellpadel.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class AdminController {

    private final CustomerService customerService;


    public AdminController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){

        List<CustomerDto> customerDtos = customerService.getAllCustomers();

        return ResponseEntity.status(HttpStatus.OK).body(customerDtos);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerPostDto dto){

        if(customerService.customerWitHUsernameAlreadyExists(dto.username(), null))
            throw new UsernameNotAvailableException("Customer with username: " + dto.username() + " already exists");

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(dto));
    }
}
