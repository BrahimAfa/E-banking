package com.ensas.ebanking.vo.converters;

import com.ensas.ebanking.models.Demande;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.vo.ClientVo;
import com.ensas.ebanking.vo.DemandeVo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DemandeConverter extends AbstractConverter<Demande, DemandeVo> {
    @Override
    public Demande toItem(DemandeVo vo) {
        if (vo == null) return null;
        Demande item = new Demande();
        item.setId(vo.getId());
        item.setAgnceToTransfer(new AgencyConverter().toItem(vo.getAgnceToTransfer()));
        item.setClient(new ClientConverter().toItem(vo.getClient()));
        item.setType(vo.getType());
        item.setAccount(new AccountConverter().toItem(vo.getAccount()));
        item.setStatus(vo.getStatus());
        item.setVille(vo.getVille());
        return item;
    }

    @Override
    public DemandeVo toVo(Demande item) {
        if (item == null) return null;
        DemandeVo vo = new DemandeVo();
        vo.setId(item.getId());
        vo.setAgnceToTransfer(new AgencyConverter().toVo(item.getAgnceToTransfer()));
        vo.setClient(new ClientConverter().toVo(item.getClient()));
        vo.setType(item.getType());
        vo.setAccount(new AccountConverter().toVo(item.getAccount()));
        vo.setStatus(item.getStatus());
        vo.setVille(item.getVille());
        return vo;
    }

}
