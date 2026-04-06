package com.simon.wigellpadel.config;

import com.simon.wigellpadel.entity.Address;
import com.simon.wigellpadel.entity.Court;
import com.simon.wigellpadel.entity.Customer;
import com.simon.wigellpadel.entity.Role;
import com.simon.wigellpadel.repository.AddressRepository;
import com.simon.wigellpadel.repository.BookingRepository;
import com.simon.wigellpadel.repository.CourtRepository;
import com.simon.wigellpadel.repository.CustomerRepository;
import com.simon.wigellpadel.service.AddressService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class DataInitializer {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CourtRepository courtRepository;
    private final BookingRepository bookingRepository;

    public DataInitializer(CustomerRepository customerRepository, AddressRepository addressRepository,
                           CourtRepository courtRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.courtRepository = courtRepository;
        this.bookingRepository = bookingRepository;
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (customerRepository.count() == 0) {
                Customer customer1 = new Customer();
                customer1.setUsername("bamsen54");
                customer1.setPassword("1234");
                customer1.setRole(Role.ADMIN);
                customer1.setFirstName("Simon");
                customer1.setLastName("Toivola");
                customer1.setAddresses(new ArrayList<>());
                customerRepository.save(customer1);

                Address address1 = new Address();
                address1.setStreet("Javastreet 24");
                address1.setCity("Jakarta");
                address1.setPostalCode("0xCAFEBABE");
                address1.setCustomer(customer1);
                addressRepository.save(address1);
                customer1.getAddresses().add(address1);
                customerRepository.save(customer1);

                Customer customer2 = new Customer();
                customer2.setUsername("dwik");
                customer2.setPassword("1234");
                customer2.setRole(Role.USER);
                customer2.setFirstName("Dennis");
                customer2.setLastName("Wiklund");
                customer2.setAddresses(new ArrayList<>());
                customerRepository.save(customer2);

                Address address2 = new Address();
                address2.setStreet("Randomstreet 67");
                address2.setCity("Stockholm");
                address2.setPostalCode("11122");
                address2.setCustomer(customer2);
                addressRepository.save(address2);
                customer2.getAddresses().add(address2);
                customerRepository.save(customer2);

                Court court1 = new Court();
                court1.setCourtName("My Padel Court");
                court1.setPricePerHourSek(500.0);
                courtRepository.save(court1);

                Court court2 = new Court();
                court2.setCourtName("Champions Court");
                court2.setPricePerHourSek(800.0);
                courtRepository.save(court2);
            }
        };
    }
}
