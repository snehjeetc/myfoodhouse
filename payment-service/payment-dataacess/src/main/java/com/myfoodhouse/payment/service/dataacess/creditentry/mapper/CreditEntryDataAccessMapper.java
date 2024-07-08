package com.myfoodhouse.payment.service.dataacess.creditentry.mapper;

import org.springframework.stereotype.Component;

import com.myfoodhouse.payment.service.dataacess.creditentry.entity.CreditEntryEntity;
import com.myfoodhouse.payment.service.domain.core.entity.CreditEntry;
import com.myfoodhouse.payment.service.domain.core.valueobject.CreditEntryId;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;
import com.myfoodhouse.sys.domain.valueobjects.Money;


@Component
public class CreditEntryDataAccessMapper {
        
    public CreditEntry creditEntryEntityToCreditEntry(CreditEntryEntity creditEntryEntity) {
        return CreditEntry.builder()
                .creditEntryId(new CreditEntryId(creditEntryEntity.getId()))
                .customerId(new CustomerId(creditEntryEntity.getCustomerId()))
                .totalCreditAmount(new Money(creditEntryEntity.getTotalCreditAmount()))
                .build();
    }

    public CreditEntryEntity creditEntryToCreditEntryEntity(CreditEntry creditEntry) {
        return CreditEntryEntity.builder()
                .id(creditEntry.getId().getValue())
                .customerId(creditEntry.getCustomerId().getValue())
                .totalCreditAmount(creditEntry.getTotalCreditAmount().getAmount())
                .build();
    }

}
