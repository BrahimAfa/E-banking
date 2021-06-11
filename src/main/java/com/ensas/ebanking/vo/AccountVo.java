package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.Transaction;
import com.ensas.ebanking.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AccountVo {
    private long id;
    private String accountNum; // 836577658273 + 12NumRandom
    private double balance=0;
    private String name;
    private String currency; // devis
    private String type; // premium or standard
    private ClientVo client;
    private Date createdAt=new Date();
    private List<TransactionVo> transactions;
}
