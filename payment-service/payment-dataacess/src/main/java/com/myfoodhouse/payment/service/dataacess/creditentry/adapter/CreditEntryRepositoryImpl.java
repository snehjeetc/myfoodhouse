package com.myfoodhouse.payment.service.dataacess.creditentry.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.myfoodhouse.payment.service.dataacess.creditentry.mapper.CreditEntryDataAccessMapper;
import com.myfoodhouse.payment.service.dataacess.creditentry.repository.CreditEntryJpaRepository;
import com.myfoodhouse.payment.service.domain.core.entity.CreditEntry;
import com.myfoodhouse.payment.service.domain.ports.output.repository.CreditEntryRepository;
import com.myfoodhouse.sys.domain.valueobjects.CustomerId;

@Component
public class CreditEntryRepositoryImpl implements CreditEntryRepository {

    private final CreditEntryJpaRepository creditEntryJpaRepository;
    private final CreditEntryDataAccessMapper creditEntryDataAccessMapper;

    public CreditEntryRepositoryImpl(CreditEntryJpaRepository creditEntryJpaRepository,
            CreditEntryDataAccessMapper creditEntryDataAccessMapper) {
        this.creditEntryJpaRepository = creditEntryJpaRepository;
        this.creditEntryDataAccessMapper = creditEntryDataAccessMapper;
    }

    @Override
    public CreditEntry save(CreditEntry creditEntry) {
        return creditEntryDataAccessMapper
                .creditEntryEntityToCreditEntry(creditEntryJpaRepository
                        .save(creditEntryDataAccessMapper.creditEntryToCreditEntryEntity(creditEntry)));
    }

    @Override
    public Optional<CreditEntry> findByCustomerId(CustomerId customerId) {
        return creditEntryJpaRepository
                .findByCustomerId(customerId.getValue())
                .map(creditEntryDataAccessMapper::creditEntryEntityToCreditEntry);
    }
}
