package com.simon.wigellpadel.repository;

import com.simon.wigellpadel.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
