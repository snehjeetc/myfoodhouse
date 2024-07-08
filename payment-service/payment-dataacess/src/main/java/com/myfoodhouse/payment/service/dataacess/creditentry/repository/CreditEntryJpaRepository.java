package com.myfoodhouse.payment.service.dataacess.creditentry.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfoodhouse.payment.service.dataacess.creditentry.entity.CreditEntryEntity;

@Repository
public interface CreditEntryJpaRepository extends JpaRepository<CreditEntryEntity, UUID> {
      Optional<CreditEntryEntity> findByCustomerId(UUID customerId);
}
