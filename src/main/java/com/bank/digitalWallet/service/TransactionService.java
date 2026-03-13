package com.bank.digitalWallet.service;

import com.bank.digitalWallet.domain.Account;
import com.bank.digitalWallet.domain.Transaction;
import com.bank.digitalWallet.repository.AccountRepository;
import com.bank.digitalWallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private AccountRepository accountRepo;

    public String saveTransaction(Transaction transaction, UUID accountId) {
        if (transaction == null) {
            return "Transaction payload is required.";
        }

        if (accountId == null) {
            return "Account id is required.";
        }

        if (transaction.getType() == null) {
            return "Transaction type is required.";
        }

        Account account = accountRepo.findById(accountId).orElse(null);
        if (account == null) {
            return "Account not found.";
        }

        transaction.setAccount(account);
        transactionRepo.save(transaction);

        return "Transaction saved successfully.";
    }

    public List<Transaction> findByAccountId(UUID accountId) {
        if (accountId == null) {
            return Collections.emptyList();
        }

        return transactionRepo.findByAccount_Id(accountId);
    }
}
