package com.bank.digitalWallet.service;

import com.bank.digitalWallet.domain.Customer;
import com.bank.digitalWallet.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public String saveCustomer(Customer customer) {
        if (customer == null) {
            return "Customer payload is required.";
        }

        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isBlank()) {
            return "Phone number is required.";
        }

        if (customerRepo.existsByPhoneNumber(customer.getPhoneNumber())) {
            return "Customer with this phone number already exists.";
        }

        customerRepo.save(customer);
        return "Customer saved successfully.";
    }

    public List<Customer> findByLocationCode(String locationCode) {
        if (locationCode == null || locationCode.isBlank()) {
            return Collections.emptyList();
        }

        return customerRepo.findByLocation_CodeIgnoreCase(locationCode);
    }

    public List<Customer> findByLocationName(String locationName) {
        if (locationName == null || locationName.isBlank()) {
            return Collections.emptyList();
        }

        return customerRepo.findByLocation_NameIgnoreCase(locationName);
    }
}
