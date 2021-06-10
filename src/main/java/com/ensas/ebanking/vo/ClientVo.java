package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.Agency;
import com.ensas.ebanking.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientVo {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String tele;
    private String CIN;
    private Set<RoleVo> roles;
    private String password; //ignore this in serializing
    private boolean isActive;
    private Agency agency = new Agency(99);
    private AgentVo agent = new AgentVo(99);

    private List<AccountVo> accounts;

}
