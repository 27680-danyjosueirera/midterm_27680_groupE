package com.bank.digitalWallet.repository;

import com.bank.digitalWallet.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    boolean existsByAccountNumber(String accountNumber);

    List<Account> findByCustomer_Id(UUID customerId);
}
