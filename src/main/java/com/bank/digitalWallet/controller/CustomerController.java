package com.bank.digitalWallet.controller;

import com.bank.digitalWallet.domain.Customer;
import com.bank.digitalWallet.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        String response = customerService.saveCustomer(customer);

        if (response.equals("Customer saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/by-location-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getByLocationCode(@RequestParam String locationCode) {
        return new ResponseEntity<>(customerService.findByLocationCode(locationCode), HttpStatus.OK);
    }

    @GetMapping(value = "/by-location-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getByLocationName(@RequestParam String locationName) {
        return new ResponseEntity<>(customerService.findByLocationName(locationName), HttpStatus.OK);
    }
}
