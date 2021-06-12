package com.ensas.ebanking.vo.converters;

import com.ensas.ebanking.models.User;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.vo.ClientDashboard;
import com.ensas.ebanking.vo.ClientVo;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class ClientConverter extends AbstractConverter<User, ClientVo> {
    @Override
    public User toItem(ClientVo vo) {
        if (vo == null) return null;
        User item = new User();
        item.setUserId(vo.getId());
        item.setEmail(vo.getEmail());
        item.setFirstname(vo.getFirstname());
        item.setActive(vo.isActive());
        item.setLastname(vo.getLastname());
        item.setCIN(vo.getCIN());
        item.setAdress(vo.getAdress());
        item.setAccounts(new AccountConverter().toItem(vo.getAccounts()));
        item.setUsername(vo.getUsername());
        if (vo.getRoles()!=null) item.setRoles(new RoleConverter().toItem(vo.getRoles().stream().collect(Collectors.toList())).stream().collect(Collectors.toSet()));
        return item;
    }

    @Override
    public ClientVo toVo(User item) {
        if (item == null) return null;
        ClientVo vo = new ClientVo();
        vo.setId(item.getUserId());
        vo.setEmail(item.getEmail());
        vo.setFirstname(item.getFirstname());
        vo.setCIN(item.getCIN());
        vo.setLastname(item.getLastname());
        vo.setActive(item.isActive());
        // System.out.println(item.getAgency());
        vo.setAgency(new AgencyConverter().toVo(item.getAgency()));
        // vo.setAgent(new AgentConverter().toVo(item.getResponsableAgent()));
        vo.setAccounts(new AccountConverter().toVo(item.getAccounts()));
        vo.setUsername(item.getUsername());
        vo.setAdress(item.getAdress());

        //  vo.setBenificiers(new BenificiereConverter().toVo(item.getBenificiers()));
        // vo.setRoles(new RoleConverter().toVo(item.getRoles().stream().collect(Collectors.toList())).stream().collect(Collectors.toSet()));
        return vo;
    }

}
