package com.ensas.ebanking.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ContractVo {
    private long id;
    private String name;
    private String type; // professionell and // jeuniss
    private Date dateD;
    private Date dateF;
    private String status;
    private ClientVo client;
}
