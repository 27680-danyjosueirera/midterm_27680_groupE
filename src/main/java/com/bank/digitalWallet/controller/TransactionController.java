package com.bank.digitalWallet.controller;

import com.bank.digitalWallet.domain.Transaction;
import com.bank.digitalWallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction, @RequestParam UUID accountId) {
        String response = transactionService.saveTransaction(transaction, accountId);

        if (response.equals("Transaction saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/by-account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> getByAccountId(@RequestParam UUID accountId) {
        return new ResponseEntity<>(transactionService.findByAccountId(accountId), HttpStatus.OK);
    }
}
