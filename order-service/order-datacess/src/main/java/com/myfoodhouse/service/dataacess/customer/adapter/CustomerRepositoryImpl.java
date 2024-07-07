package com.myfoodhouse.service.dataacess.customer.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.myfoodhouse.order.domain.core.entity.Customer;
import com.myfoodhouse.ports.output.repository.CustomerRepository;
import com.myfoodhouse.service.dataacess.customer.mapper.CustomerDataAccessMapper;
import com.myfoodhouse.service.dataacess.customer.repository.CustomerJpaRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
 
    private final CustomerDataAccessMapper customerDataAccessMapper; 
    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryImpl(CustomerDataAccessMapper customerDataAccessMapper, CustomerJpaRepository customerJpaRepository) {
        this.customerDataAccessMapper = customerDataAccessMapper;
        this.customerJpaRepository = customerJpaRepository;
    }
    
    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId).map(customerDataAccessMapper::customerEntityToCustomer);
    }
    
}
