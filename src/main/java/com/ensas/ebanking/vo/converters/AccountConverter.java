package com.ensas.ebanking.vo.converters;

import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.vo.AccountVo;
import com.ensas.ebanking.vo.ClientVo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountConverter extends AbstractConverter<Account, AccountVo> {
    @Override
    public Account toItem(AccountVo vo) {
        if (vo == null) return null;
        Account item = new Account();
        item.setName(vo.getName());
        item.setAccountNum(vo.getAccountNum());
        item.setBalance(vo.getBalance());
        item.setId(vo.getId());
        item.setType(vo.getType());
        item.setCreatedAt(vo.getCreatedAt());
        item.setCurrency(vo.getCurrency());
        item.setClient(new ClientConverter().toItem(vo.getClient()));
        item.setTransactions(new TransactionConverter().toItem(vo.getTransactions()));
        return item;
    }

    @Override
    public AccountVo toVo(Account item) {
        if (item == null) return null;
        AccountVo vo = new AccountVo();
        vo.setName(item.getName());
        vo.setAccountNum(item.getAccountNum());
        vo.setBalance(item.getBalance());
        vo.setId(item.getId());
        vo.setType(item.getType());
        vo.setCreatedAt(item.getCreatedAt());
        vo.setCurrency(item.getCurrency());
        // vo.setClient(new ClientConverter().toVo(item.getClient()));
        vo.setTransactions(new TransactionConverter().toVo(item.getTransactions()));
        return vo;
    }
}
