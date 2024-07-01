package com.myfoodhouse.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import com.myfoodhouse.order.domain.core.entity.Customer;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customer); 
}
