package com.myfoodhouse.customer.service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfoodhouse.customer.service.entity.CustomerEntity;

@Repository
public interface  CustomerRepository extends JpaRepository<CustomerEntity, UUID>{
    
}
