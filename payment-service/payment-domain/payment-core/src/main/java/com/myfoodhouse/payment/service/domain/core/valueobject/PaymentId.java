package com.myfoodhouse.payment.service.domain.core.valueobject;

import java.util.UUID;

import com.myfoodhouse.sys.domain.valueobjects.BaseId;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
