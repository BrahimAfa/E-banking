package com.ensas.ebanking.vo.converters;

import com.ensas.ebanking.models.Benificier;
import com.ensas.ebanking.models.Transaction;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.vo.ClientVo;
import com.ensas.ebanking.vo.TransactionVo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TransactionConverter extends AbstractConverter<Transaction, TransactionVo> {
    @Override
    public Transaction toItem(TransactionVo vo) {
        if (vo == null) return null;
        Transaction item = new Transaction();
        item.setId(vo.getId());
        item.setName(vo.getName());
        item.setType(vo.getType());
        item.setAmount(vo.getAmount());
        item.setCretaedAt(vo.getCretaedAt());
        item.setDescription(vo.getDescription());
        item.setTransactionType(vo.getTransactionType());
        item.setBenificier(new BenificierConverter().toItem(vo.getBenificier()));
        item.setAccount(new AccountConverter().toItem(vo.getAccount()));
        return item;
    }

    @Override
    public TransactionVo toVo(Transaction item) {
        if (item == null) return null;
        TransactionVo vo = new TransactionVo();
        vo.setId(item.getId());
        vo.setName(item.getName());
        vo.setType(item.getType());
        vo.setAmount(item.getAmount());
        vo.setCretaedAt(item.getCretaedAt());
        vo.setDescription(item.getDescription());
        vo.setTransactionType(item.getTransactionType());
        vo.setBenificier(new BenificierConverter().toVo(item.getBenificier()));
        // vo.setAccount(new AccountConverter().toVo(item.getAccount()));
        return vo;
    }
}
