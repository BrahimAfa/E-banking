package com.ensas.ebanking.vo;

import com.ensas.ebanking.vo.AccountVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDashboard {
    private Double credit;
    private Double debit;
    private Double blocked;
    private Double faild;
    private List<AccountVo> accounts;
}
