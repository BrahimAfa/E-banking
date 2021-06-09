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
    private String type;
    private Date dateD;
    private Date dateF;
    private String status;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User client;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agnet_id", nullable = false)
    private User agent; // who created the contract


}
