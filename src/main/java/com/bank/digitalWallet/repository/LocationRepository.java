package com.bank.digitalWallet.repository;

import com.bank.digitalWallet.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {

    boolean existsByCode(String code);

    Optional<Location> findByCodeIgnoreCase(String code);

}
