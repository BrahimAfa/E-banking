package com.ensas.ebanking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
@Data
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private double amount;
    private String transactionType; // debit + or credit -;
    private String type; // Factuer, Virment
    private String name;
    private String description;
    private Date cretaedAt = new Date();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "benificier_id")
    private Benificier benificier; // brahim | null in cas of facture

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account; // dyal raji // hada ghadi tan9as meno l amount
}
