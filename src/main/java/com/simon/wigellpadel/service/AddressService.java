package com.simon.wigellpadel.service;

import com.simon.wigellpadel.entity.Address;
import com.simon.wigellpadel.exception.AddressDoesNotExistException;
import com.simon.wigellpadel.repository.AddressRepository;
import com.simon.wigellpadel.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final Logger logger = LoggerFactory.getLogger(AddressService.class);;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void postAddress(Address address) {

        addressRepository.findById(address.getId()).orElseThrow(() -> {
            logger.error("Address with id {} not found - creation attempt rejected", address.getId());
            throw new AddressDoesNotExistException(address.getId());
        });

        addressRepository.save(address);
        logger.info("Created address with id: {}", address.getId());
    }

    public void deleteAddress(Address address) {

        addressRepository.findById(address.getId()).orElseThrow(() -> {
            logger.error("Address with id {} not found - deletion attempt rejected", address.getId());
            throw new AddressDoesNotExistException(address.getId());
        });

        addressRepository.delete(address);
        logger.info("Deleted address with id: {} from customer with id: {}", address.getId(), address.getCustomer().getId());
    }
}
