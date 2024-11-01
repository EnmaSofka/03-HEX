package com.bank.management;

import com.bank.management.config.PostgresBankAccountRepository;
import com.bank.management.data.AccountEntity;
import com.bank.management.gateway.AccountRepository;
import com.bank.management.mapper.BankAccountMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BankAccountAdapter implements AccountRepository {

    private final PostgresBankAccountRepository bankAccountRepository;

    public BankAccountAdapter(PostgresBankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public Optional<Account> findById(Long id) {
        Optional<AccountEntity> bankAccountFound = bankAccountRepository.findById(id);
        return bankAccountFound.map(BankAccountMapper::toDomain);
    }

    @Override
    public Optional<Account> save(Account account) {
        AccountEntity entity = BankAccountMapper.toEntity(account);
        AccountEntity savedEntity = bankAccountRepository.save(entity);
        Account savedDomain = BankAccountMapper.toDomain(savedEntity);
        return Optional.ofNullable(savedDomain);
    }

    @Override
    public boolean delete(Long id) {
        Optional<AccountEntity> bankAccountFound = bankAccountRepository.findById(id);
        if (bankAccountFound.isEmpty()) {return false;}
        bankAccountRepository.delete(bankAccountFound.get());
        return true;
    }

    @Override
    public Optional<Account> findByNumber(String accountNumber) {
        Optional<AccountEntity> bankAccountEntityOptional = bankAccountRepository.findByNumber(accountNumber);
        if (bankAccountEntityOptional.isEmpty()) {return Optional.empty();}
        AccountEntity bankAccountEntity  = bankAccountEntityOptional.get();
        Account account = BankAccountMapper.toDomain(bankAccountEntity);
        return Optional.ofNullable(account);
    }

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        List<AccountEntity> bankAccountEntities = bankAccountRepository.findByCustomerId(customerId);
        return bankAccountEntities.stream()
                .map(BankAccountMapper::toDomain)
                .collect(Collectors.toList());
    }
}
