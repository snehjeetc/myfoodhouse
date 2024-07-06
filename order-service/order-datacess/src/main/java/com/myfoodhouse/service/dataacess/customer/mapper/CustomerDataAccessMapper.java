package com.myfoodhouse.service.dataacess.customer.mapper;

import org.springframework.stereotype.Component;

import com.myfoodhouse.order.domain.core.entity.Customer;
import com.myfoodhouse.service.dataacess.customer.entity.CustomerEntity;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;

@Component
public class CustomerDataAccessMapper {
       public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
