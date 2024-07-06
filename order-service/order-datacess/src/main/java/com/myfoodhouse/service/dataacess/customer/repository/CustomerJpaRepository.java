package com.myfoodhouse.service.dataacess.customer.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfoodhouse.service.dataacess.customer.entity.CustomerEntity;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
    // Optional<CustomerEntity> findCustomerById(UUID id); 
}
