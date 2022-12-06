package rga.example.little.banker.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String surname;

    private String sex;

    private String nationality;

    private Date birthDate;

    private String cardNumber;

    private Date cardIssueDate;

    private Date cardExpiryDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

}
