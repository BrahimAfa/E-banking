package com.ensas.ebanking.vo;


import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.Agency;
import com.ensas.ebanking.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DemandeVo {
    private long id;
    private String ville;
    private String status; // pending ; accteped ==> accepted change agency or declined; Cancelled
    private String type; // transfer
    private ClientVo client;
    private AccountVo account; // li ghadi n0so meno 55 dh;
    private AgencyVo agnceToTransfer;
    private String motif;
}
