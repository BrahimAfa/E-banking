package com.ensas.ebanking.vo.converters;

import com.ensas.ebanking.models.Agency;
import com.ensas.ebanking.models.Agency;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.vo.AgencyVo;
import com.ensas.ebanking.vo.AgencyVo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AgencyConverter extends AbstractConverter<Agency, AgencyVo> {
    @Override
    public Agency toItem(AgencyVo vo) {
        if (vo == null) return null;
        Agency item = new Agency();
        item.setName(vo.getName());
        item.setAdress(vo.getAdress());
        item.setTele(vo.getAdress());
        item.setId(vo.getId());
        item.setVille(vo.getVille());
        item.setUsers(new ClientConverter().toItem(vo.getUsers()));
        return item;
    }

    @Override
    public AgencyVo toVo(Agency item) {
        if (item == null) return null;
        AgencyVo vo = new AgencyVo();
        vo.setName(item.getName());
        vo.setAdress(item.getAdress());
        vo.setTele(item.getAdress());
        vo.setId(item.getId());
        vo.setVille(item.getVille());
        // vo.setUsers(new ClientConverter().toVo(item.getUsers()));
        return vo;
    }

}
