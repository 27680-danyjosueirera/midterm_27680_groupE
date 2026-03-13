package com.bank.digitalWallet.repository;

import com.bank.digitalWallet.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByAccount_Id(UUID accountId);

}
