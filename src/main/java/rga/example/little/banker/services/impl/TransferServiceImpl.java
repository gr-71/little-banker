package rga.example.little.banker.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rga.example.little.banker.entities.Account;
import rga.example.little.banker.entities.Transfer;
import rga.example.little.banker.exceptions.NotEnoughMoneyException;
import rga.example.little.banker.exceptions.NotFoundException;
import rga.example.little.banker.repositories.AccountRepository;
import rga.example.little.banker.repositories.TransferRepository;
import rga.example.little.banker.services.TransferService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    @NonNull
    private TransferRepository transferRepository;

    @NonNull
    private AccountRepository accountRepository;

    @Override
    @Transactional(rollbackOn = NotEnoughMoneyException.class)
    public void moneyTransfer(Transfer transfer) {
        var destinationAccount = amountIncrease(transfer.getDebtorAccount().getIban(), transfer.getAmount());
        var sourceAccount = amountIncrease(transfer.getCreditorAccount().getIban(), -transfer.getAmount());
        accountRepository.save(destinationAccount);
        accountRepository.save(sourceAccount);
        transferRepository.save(transfer);
    }

    @Override
    public List<Transfer> findByAmount(Double amount) {
        return transferRepository.findByAmount(amount);
    }

    @Override
    public List<Transfer> findByIban(String iban) {
        return transferRepository.findByIban(iban);
    }

    @Override
    public List<Transfer> findByMessage(String message) {
        return transferRepository.findByMessage(message);
    }

    @Transactional
    public Account amountIncrease(String iban, Double amount) {
        var account = accountRepository.findById(iban).orElseThrow(() -> {
            log.warn("There is no account with IBAN: {} ", iban);
            throw new NotFoundException();
        });
        if (account.getBalance() + amount < 0) {
            log.warn("There is not enough money on account with IBAN: {} ", iban);
            throw new NotEnoughMoneyException();
        }
        var newAmount = account.getBalance() + amount;
        account.setBalance(newAmount);
        return account;
    }
}
