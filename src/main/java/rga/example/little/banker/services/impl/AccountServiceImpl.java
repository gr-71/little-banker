package rga.example.little.banker.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rga.example.little.banker.entities.Account;
import rga.example.little.banker.exceptions.AlreadyExistsException;
import rga.example.little.banker.exceptions.NotFoundException;
import rga.example.little.banker.repositories.AccountRepository;
import rga.example.little.banker.services.AccountService;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @NonNull
    private AccountRepository repository;

    @Override
    @Transactional
    public void create(Account account) {
        if(repository.existsById(account.getIban())){
            log.warn("Account with IBAN: {} already exists in the system!", account.getIban());
            throw new AlreadyExistsException();
        }
        repository.save(account);
    }

    @Override
    @Transactional
    public void modify(Account account) {
        if(!repository.existsById(account.getIban())){
            log.warn("There is no account with IBAN: {} in the system!", account.getIban());
            throw new NotFoundException();
        }
        repository.save(account);    }

    @Override
    @Transactional
    public void removeById(String iban) {
        if(!repository.existsById(iban)){
            log.warn("There is no account with IBAN: {} to remove!", iban);
            throw new NotFoundException();
        }
        repository.deleteById(iban);
    }

    @Override
    public Account viewSummaryByIban(String iban) {
        return repository.findById(iban).orElseThrow(() -> {
            log.warn("There is no account with IBAN: {} in the system!", iban);
            throw new NotFoundException();
        });
    }

    @Override
    public Double viewBalanceByIban(String iban) {
        if(!repository.existsById(iban)){
            log.warn("There is no account with IBAN: {} to view summary!", iban);
            throw new NotFoundException();
        }
        var foundAccount = viewSummaryByIban(iban);
        return foundAccount.getBalance();
    }

}
