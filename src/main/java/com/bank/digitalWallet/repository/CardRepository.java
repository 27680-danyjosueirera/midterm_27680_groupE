package com.bank.digitalWallet.repository;

import com.bank.digitalWallet.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {

    boolean existsByCardNumber(String cardNumber);

    List<Card> findByCustomer_Id(UUID customerId);

}
