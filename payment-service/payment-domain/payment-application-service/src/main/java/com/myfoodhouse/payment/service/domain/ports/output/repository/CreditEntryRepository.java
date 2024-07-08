package com.myfoodhouse.payment.service.domain.ports.output.repository;

import java.util.Optional;

import com.myfoodhouse.payment.service.domain.core.entity.CreditEntry;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;

public interface CreditEntryRepository {
     CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
