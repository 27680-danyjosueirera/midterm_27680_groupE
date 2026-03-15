package com.bank.digitalWallet.repository;

import com.bank.digitalWallet.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    boolean existsByPhoneNumber(String phoneNumber);

    List<Customer> findByLocation_CodeIgnoreCase(String provinceCode);

    List<Customer> findByLocation_NameIgnoreCase(String provinceName);

    List<Customer> findByLocation_IdIn(Collection<UUID> locationIds);

}
