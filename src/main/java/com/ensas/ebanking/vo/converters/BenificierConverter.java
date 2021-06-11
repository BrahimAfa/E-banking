package com.ensas.ebanking.vo.converters;

import com.ensas.ebanking.models.Benificier;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.vo.BenificierVo;
import com.ensas.ebanking.vo.ClientVo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BenificierConverter extends AbstractConverter<Benificier, BenificierVo> {
    @Override
    public Benificier toItem(BenificierVo vo) {
        if (vo == null) return null;
        Benificier item = new Benificier();
        item.setId(vo.getId());
        item.setTele(vo.getTele());
        item.setEmail(vo.getEmail());
        item.setLastName(vo.getLastName());
        item.setFirstname(vo.getFirstname());
        item.setAccountNum(vo.getAccountNum());
        item.setClient(new ClientConverter().toItem(vo.getClient()));
        return item;
    }

    @Override
    public BenificierVo toVo(Benificier item) {
        if (item == null) return null;
        BenificierVo vo = new BenificierVo();
        vo.setId(item.getId());
        vo.setTele(item.getTele());
        vo.setEmail(item.getEmail());
        vo.setLastName(item.getLastName());
        vo.setFirstname(item.getFirstname());
        vo.setAccountNum(item.getAccountNum());
        // vo.setClient(new ClientConverter().toVo(item.getClient()));
        return vo;
    }
}
