package com.myfoodhouse.restaurant.service.domain.core.valueobject;

import java.util.UUID;

import com.myfoodhouse.sys.domain.valueobjects.BaseId;

public class OrderApprovalId extends BaseId<UUID> {

    public OrderApprovalId(UUID value) {
        super(value);
    }
    
}
