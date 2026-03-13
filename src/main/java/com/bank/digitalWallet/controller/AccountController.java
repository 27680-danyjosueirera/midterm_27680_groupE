package com.bank.digitalWallet.controller;

import com.bank.digitalWallet.domain.Account;
import com.bank.digitalWallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAccount(@RequestBody Account account) {
        String response = accountService.saveCustomerAccount(account);

        if (response.equals("Account saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/by-customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> getByCustomerId(@RequestParam UUID customerId) {
        return new ResponseEntity<>(accountService.findByCustomerId(customerId), HttpStatus.OK);
    }
}
