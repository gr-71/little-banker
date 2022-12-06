package rga.example.little.banker.services;

import rga.example.little.banker.entities.Account;

public interface AccountService {

    void create(Account account);

    void modify(Account account);

    void removeById (String iban);


    Account viewSummaryByIban(String iban);

    Double viewBalanceByIban(String iban);

}
