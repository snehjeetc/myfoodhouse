package com.myfoodhouse.payment.service.domain.core.valueobject;

import java.util.UUID;

import com.myfoodhouse.sys.domain.valueobjects.BaseId;

public class CreditHistoryId extends BaseId<UUID>{
    public CreditHistoryId(UUID value) {
        super(value);
    }
}
