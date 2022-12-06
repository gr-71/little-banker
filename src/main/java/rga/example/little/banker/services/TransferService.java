package rga.example.little.banker.services;

import rga.example.little.banker.entities.Transfer;

import java.util.List;

public interface TransferService {

    void moneyTransfer(Transfer transfer);

    List<Transfer> findByAmount(Double amount);

    List<Transfer> findByIban(String iban);

    List<Transfer> findByMessage (String message);

}
