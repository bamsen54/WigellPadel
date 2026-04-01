package com.simon.wigellpadel.service;

import com.simon.wigellpadel.dto.CustomerDto;
import com.simon.wigellpadel.dto.PostCustomerDto;
import com.simon.wigellpadel.entity.Customer;
import com.simon.wigellpadel.exception.CustomerDoesNotExistException;
import com.simon.wigellpadel.exception.UsernameNotAvailableException;
import com.simon.wigellpadel.mapper.CustomerMapper;
import com.simon.wigellpadel.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper     = customerMapper;
    }

    public CustomerDto findById(long id) {
        return CustomerMapper.toDto(customerRepository.findById(id).orElseThrow( () -> new CustomerDoesNotExistException(id) ));
    }

    public Customer findCustomerEntityById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerDoesNotExistException(id));
    }

    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toDto).toList();
    }

    public CustomerDto save(PostCustomerDto dto) {

        logger.info("Attemping creation of customer with username: {}", dto.username());

        if(customerWitHUsernameAlreadyExists(dto.username(), null)) {
            logger.warn("Username '{}' already exists in database - creation attempt rejected", dto.username());
            throw new UsernameNotAvailableException(dto.username() + " is already taken");
        }

        Customer customer = customerMapper.fromPostCustomerDtoToCustomer(dto);
        Customer savedCustomer = customerRepository.save(customer);

        logger.info("Created new customer with id: {} and username: {}", savedCustomer.getId(), savedCustomer.getUsername());

        return CustomerMapper.toDto(savedCustomer);
    }

    public CustomerDto save(Customer customer) {

        logger.info("Attemping creation of customer with username: {}", customer.getUsername());

        if(customerWitHUsernameAlreadyExists(customer.getUsername(), customer.getId())) {
            throw new UsernameNotAvailableException(customer.getUsername() + " is already taken");
        }

        Customer savedCustomer = customerRepository.save(customer);

        logger.info("Created new customer with id: {} and username: {}", savedCustomer.getId(), savedCustomer.getUsername());

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
