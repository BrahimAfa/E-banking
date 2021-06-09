package com.ensas.ebanking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
@Data
@ToString
public class Account {
    @Id
    private long id;
    private String accountNum; // 836577658273 + 12NumRandom
    private double balance;
    private String name;
    private String currency;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
