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
        item.setLastname(vo.getLastname());
        item.setCIN(vo.getCIN());
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
        vo.setAccounts(new AccountConverter().toVo(item.getAccounts()));
        vo.setUsername(item.getUsername());
        // vo.setRoles(new RoleConverter().toVo(item.getRoles().stream().collect(Collectors.toList())).stream().collect(Collectors.toSet()));
        return vo;
    }

}
