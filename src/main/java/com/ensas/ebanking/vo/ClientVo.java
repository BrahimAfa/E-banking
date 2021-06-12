package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.Agency;
import com.ensas.ebanking.models.Benificier;
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
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String tele;
    private String adress;
    private String CIN;
    private boolean isActive=true;
    private String password; //ignore this in serializing
    private Set<RoleVo> roles;
    private AgencyVo agency;
    private AgentVo agent;
    private List<BenificierVo> benificiers;

    private List<AccountVo> accounts;

}
