package com.ensas.ebanking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BENIFICIER")
@Data
@ToString
public class Benificier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String accountNum;
    private String firstname;
    private String lastName;
    private String tele;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private User client; // from jwt

}
