package com.ensas.ebanking.vo.converters;

import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.vo.RoleVo;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter extends AbstractConverter<Role, RoleVo> {
    @Override
    public Role toItem(RoleVo vo) {
        if (vo == null) return null;
        Role item = new Role();
        item.setRole_id(vo.getId());
        item.setName(item.getName());
        item.setActive(item.isActive());
        return item;
    }

    @Override
    public RoleVo toVo(Role item) {
        if (item == null) return null;
        RoleVo vo = new RoleVo();
        vo.setId(item.getRole_id());
        vo.setName(item.getName());
        vo.setActive(item.isActive());
        return vo;
    }
}
