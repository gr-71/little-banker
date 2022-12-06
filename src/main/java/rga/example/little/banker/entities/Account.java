package rga.example.little.banker.entities;

import lombok.*;
import rga.example.little.banker.enums.Currency;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Account {

    @Id
    private String iban;

    private Currency currency = Currency.EUR;

    private Double balance = 7000.00;

}
