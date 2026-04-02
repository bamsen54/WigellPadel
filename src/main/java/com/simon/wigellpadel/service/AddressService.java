package com.simon.wigellpadel.service;

import com.simon.wigellpadel.entity.Address;
import com.simon.wigellpadel.repository.AddressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void  postAddress(Address address) {
        addressRepository.save(address);
    }

    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }
}
