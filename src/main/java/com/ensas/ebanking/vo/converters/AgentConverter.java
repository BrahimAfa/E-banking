package com.ensas.ebanking.vo.converters;

import com.ensas.ebanking.models.User;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.vo.AgentVo;
import com.ensas.ebanking.vo.AgentVo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AgentConverter extends AbstractConverter<User, AgentVo> {
    @Override
    public User toItem(AgentVo vo) {
        if (vo == null) return null;
        User item = new User();
        item.setUserId(vo.getId());
        item.setEmail(vo.getEmail());
        item.setFirstname(vo.getFirstname());
        item.setLastname(vo.getLastname());
        item.setCIN(vo.getCIN());
        // item.setAccounts(new AccountConverter().toItem(vo.getAccounts()));
        item.setUsername(vo.getUsername());
        // if (vo.getRoles()!=null) item.setRoles(new RoleConverter().toItem(vo.getRoles().stream().collect(Collectors.toList())).stream().collect(Collectors.toSet()));
        return item;
    }

    @Override
    public AgentVo toVo(User item) {
        if (item == null) return null;
        AgentVo vo = new AgentVo();
        vo.setId(item.getUserId());
        vo.setEmail(item.getEmail());
        vo.setFirstname(item.getFirstname());
        vo.setCIN(item.getCIN());
        vo.setLastname(item.getLastname());
        vo.setAgency(new AgencyConverter().toVo(item.getAgency()));
        vo.setUsername(item.getUsername());
        // vo.setAgent();new AgentC().toVo(item.getAgency()));
        // vo.setAccounts(new AccountConverter().toVo(item.getAccounts()));
        // vo.setRoles(new RoleConverter().toVo(item.getRoles().stream().collect(Collectors.toList())).stream().collect(Collectors.toSet()));
        return vo;
    }

}
