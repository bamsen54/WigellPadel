package com.simon.wigellpadel.service;

import com.simon.wigellpadel.dto.CustomerDto;
import com.simon.wigellpadel.dto.PostCustomerDto;
import com.simon.wigellpadel.dto.PutCustomerDto;
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
        return CustomerMapper.toDto(customerRepository.findById(id).orElseThrow(() -> {
            logger.error("Customer with id: {} not found", id);
            throw new CustomerDoesNotExistException(id);
        }));
    }

    public Customer findCustomerEntityById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Customer with id: {} not found", id);
                    throw new CustomerDoesNotExistException(id);
                });
    }

    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toDto).toList();
    }

    public CustomerDto save(PostCustomerDto dto) {

        if(customerWithUsernameAlreadyExists(dto.username(), null)) {
            logger.warn("Username: '{}' already exists in database - creation attempt rejected", dto.username());
            throw new UsernameNotAvailableException(dto.username() + " is already taken");
        }

        Customer customer = customerMapper.fromPostCustomerDtoToCustomer(dto);
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toDto(savedCustomer);
    }

    public CustomerDto save(Customer customer) {

        if(customerWithUsernameAlreadyExists(customer.getUsername(), customer.getId())) {
            logger.warn("Username: '{}' already exists in database - creation attempt rejected", customer.getUsername());
            throw new UsernameNotAvailableException(customer.getUsername() + " is already taken");
        }

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toDto(savedCustomer);
    }

    @Transactional
    public CustomerDto update(Long id, PutCustomerDto dto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            logger.warn("Customer with id {} not found", id);
            throw new CustomerDoesNotExistException(id);
        });

        if(customerWithUsernameAlreadyExists(dto.username(), customer.getId())) {
            logger.warn("Username '{}' already exists in database - update attempt rejected", dto.username());
            throw  new UsernameNotAvailableException(dto.username() + " is already taken");
        }

        customer.setUsername(dto.username());
        customer.setPassword(dto.password());
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setRole(dto.role());

        Customer saved = customerRepository.save(customer);
        logger.info("Updated customer with id: {}", id);

        return CustomerMapper.toDto(saved);
    }

    public void delete(Long id) {

        customerRepository.findById(id).orElseThrow(() -> {
            logger.warn("Customer with id {} does not exist", id);
            throw new CustomerDoesNotExistException(id);
        });

        logger.info("Updated customer with id: {}", id);


        customerRepository.deleteById(id);}

    public boolean customerWithUsernameAlreadyExists(String username, Long id) {

        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {

            if (customer.getUsername().equals(username) && !customer.getId().equals(id))
                return true;
        }

        return false;
    }
}
