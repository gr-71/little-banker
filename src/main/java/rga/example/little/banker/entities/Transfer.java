package rga.example.little.banker.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Transfer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Date date;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "debtor_iban")
    private Account debtorAccount;

    @ManyToOne
    @JoinColumn(name = "creditor_iban")
    private Account creditorAccount;

    private String message;
}
