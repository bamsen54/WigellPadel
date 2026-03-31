package com.simon.wigellpadel.mapper;

import com.simon.wigellpadel.dto.AddressDto;
import com.simon.wigellpadel.entity.Address;

public class AddressMapper {

    public static AddressDto toDto(Address address) {
        if (address == null)
            return null;

        return new AddressDto(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getPostalCode()
        );
    }

    public static Address fromDto(AddressDto dto) {
        if (dto == null)
            return null;

        Address address = new Address();
        address.setStreet(dto.street());
        address.setCity(dto.city());
        address.setPostalCode(dto.postalCode());

        return address;
    }
}
