package com.bank.management;

import com.bank.management.config.PostgresBankAccountRepository;
import com.bank.management.data.BankAccountEntity;
import com.bank.management.gateway.BankAccountRepository;
import com.bank.management.mapper.BankAccountMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BankAccountAdapter implements BankAccountRepository {

    private final PostgresBankAccountRepository bankAccountRepository;

    public BankAccountAdapter(PostgresBankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount findById(Long id) {
        Optional<BankAccountEntity> bankAccountFound = bankAccountRepository.findById(id);
        return bankAccountFound.map(BankAccountMapper::toDomain).orElse(null);
    }

    @Override
    public BankAccount save(BankAccount account) {
        BankAccountEntity entity = BankAccountMapper.toEntity(account);
        BankAccountEntity savedEntity = bankAccountRepository.save(entity);
        return BankAccountMapper.toDomain(savedEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<BankAccountEntity> bankAccountFound = bankAccountRepository.findById(id);
        bankAccountRepository.delete(bankAccountFound.get());
    }

    @Override
    public BankAccount findByAccountNumber(String accountNumber) {
        Optional<BankAccountEntity> bankAccountEntityOptional = bankAccountRepository.findByAccountNumber(accountNumber);
        BankAccountEntity bankAccountEntity  = bankAccountEntityOptional.get();
        BankAccount bankAccount = BankAccountMapper.toDomain(bankAccountEntity);
        return bankAccount;
    }

    @Override
    public List<BankAccount> findByCustomerId(Long customerId) {
        List<BankAccountEntity> bankAccountEntities = bankAccountRepository.findByCustomerId(customerId);
        return bankAccountEntities.stream()
                .map(BankAccountMapper::toDomain)
                .collect(Collectors.toList());
    }
}
