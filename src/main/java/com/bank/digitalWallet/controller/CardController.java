package com.bank.digitalWallet.controller;

import com.bank.digitalWallet.domain.Card;
import com.bank.digitalWallet.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCard(@RequestBody Card card, @RequestParam UUID customerId) {
        String response = cardService.saveCard(card, customerId);

        if (response.equals("Card saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/by-customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Card>> getByCustomerId(@RequestParam UUID customerId) {
        return new ResponseEntity<>(cardService.findByCustomerId(customerId), HttpStatus.OK);
    }
}
