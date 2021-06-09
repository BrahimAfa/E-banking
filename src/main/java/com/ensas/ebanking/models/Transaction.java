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
    private long id;
    private double amount;
    private String transactionType; // debit + or credit -;
    private String type; // Factuer, Virment
    private String name;
    private String description;
    private Date cretaedAt = new Date();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private User client; // raji | brahim

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "benificier_id")
    private Benificier benificier; // brahim | null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account; // dyal raji  |


}
