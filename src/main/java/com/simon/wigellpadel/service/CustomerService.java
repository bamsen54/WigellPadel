package com.simon.wigellpadel.service;

import com.simon.wigellpadel.dto.CustomerDto;
import com.simon.wigellpadel.dto.CustomerPostDto;
import com.simon.wigellpadel.entity.Customer;
import com.simon.wigellpadel.mapper.CustomerMapper;
import com.simon.wigellpadel.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDto> customersDto = customers.stream().map(CustomerMapper::toDto).toList();

        return customersDto;
    }

    public CustomerDto addCustomer(CustomerPostDto dto) {
        Customer customer      = CustomerMapper.fromPostDto(dto);
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toDto(savedCustomer);
    }

    public boolean customerWitHUsernameAlreadyExists(String username, Long id) {

        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {

            if (customer.getUsername().equals(username) && !customer.getId().equals(id))
                return true;
        }

        return false;
    }
}
