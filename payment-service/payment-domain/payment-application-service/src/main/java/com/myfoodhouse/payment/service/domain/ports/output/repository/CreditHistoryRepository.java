package com.myfoodhouse.payment.service.domain.ports.output.repository;

import java.util.List;
import java.util.Optional;

import com.myfoodhouse.payment.service.domain.core.entity.CreditHistory;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;

public interface CreditHistoryRepository {
    CreditHistory save(CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
