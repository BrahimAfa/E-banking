package com.ensas.ebanking.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEMANDE")
@Data
@ToString
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ville;
    private String status; // pending ; accteped ==> accepted change agency or declined; Cancelled
    private String type; // transfer

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account; // li ghadi n0so meno 55 dh;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agence_id", nullable = false)
    private Agency agnceToTransfer;
}
