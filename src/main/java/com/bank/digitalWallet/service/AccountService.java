package com.bank.digitalWallet.service;

import com.bank.digitalWallet.domain.Account;
import com.bank.digitalWallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    public String saveCustomerAccount(Account account) {
        if (account == null) {
            return "Account payload is required.";
        }

        if (account.getAccountNumber() == null || account.getAccountNumber().isBlank()) {
            return "Account number is required.";
        }

        if (account.getType() == null) {
            return "Account type is required.";
        }

        if (accountRepo.existsByAccountNumber(account.getAccountNumber())) {
            return "Account with this account number already exists.";
        }

        accountRepo.save(account);
        return "Account saved successfully.";
    }

    public List<Account> findByCustomerId(UUID customerId) {
        if (customerId == null) {
            return Collections.emptyList();
        }

        return accountRepo.findByCustomer_Id(customerId);
    }
}

