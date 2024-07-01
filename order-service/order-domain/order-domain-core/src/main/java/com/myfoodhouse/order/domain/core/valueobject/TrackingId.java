package com.myfoodhouse.order.domain.core.valueobject;

import java.util.UUID;

import com.myfoodhouse.sys.domain.valueobjects.BaseId;

public class TrackingId extends BaseId<UUID>{

    public TrackingId(UUID value) {
        super(value);
    }
    
}
