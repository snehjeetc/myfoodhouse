package com.myfoodhouse.payment.service.domain.core.valueobject;

import java.util.UUID;

import com.myfoodhouse.sys.domain.valueobjects.BaseId;

public class CreditEntryId extends BaseId<UUID>{
    public CreditEntryId(UUID value) {
        super(value);
    }
}
