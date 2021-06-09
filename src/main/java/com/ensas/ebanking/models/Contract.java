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
@Table(name = "CONTRACT")
@Data
@ToString
public class Contract {
    @Id
    private long id;
    private String name;
    private String status; // Blocked, Terminated, Active
    private Date dateD = new Date();
    private Date dateF;// + NOW + 5ANS => WICH WILL BE A GLOBAL PARAMETRE
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User client;



}
