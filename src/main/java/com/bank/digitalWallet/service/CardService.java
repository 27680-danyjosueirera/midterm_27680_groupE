package com.bank.digitalWallet.service;

import com.bank.digitalWallet.domain.Card;
import com.bank.digitalWallet.domain.Customer;
import com.bank.digitalWallet.repository.CardRepository;
import com.bank.digitalWallet.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private CustomerRepository customerRepo;

    public String saveCard(Card card, UUID customerId) {
        if (card == null) {
            return "Card payload is required.";
        }

        if (customerId == null) {
            return "Customer id is required.";
        }

        if (card.getCardNumber() == null || card.getCardNumber().isBlank()) {
            return "Card number is required.";
        }

        if (card.getStatus() == null) {
            return "Card status is required.";
        }

        if (cardRepo.existsByCardNumber(card.getCardNumber())) {
            return "Card with this card number already exists.";
        }

        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            return "Customer not found.";
        }

        if (!cardRepo.findByCustomer_Id(customerId).isEmpty()) {
            return "Customer already has a card.";
        }

        card.setCustomer(optionalCustomer.get());
        cardRepo.save(card);
        return "Card saved successfully.";
    }

    public List<Card> findByCustomerId(UUID customerId) {
        if (customerId == null) {
            return Collections.emptyList();
        }

        return cardRepo.findByCustomer_Id(customerId);
    }
}
