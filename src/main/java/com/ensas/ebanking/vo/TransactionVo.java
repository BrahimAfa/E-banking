package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TransactionVo {
    private long id;
    private double amount;
    private String transactionType; // debit + or credit -;
    private String type; // Factuer, Virment
    private String name;
    private String description;
    private Date cretaedAt = new Date();
    private ClientVo client; // raji | brahim
    private BenificierVo benificier; // brahim | null in cas of facture
    private AccountVo account; // dyal raji // hada ghadi tan9as meno l amount
}
