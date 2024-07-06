package com.myfoodhouse.order.domain.core.entity;

import com.myfoodhouse.sys.domain.entity.AggregateRoot;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

    public Customer() { 
        
    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }

    
}
