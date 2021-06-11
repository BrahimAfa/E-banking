package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentVo {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String tele;
    private String CIN;
    private Set<RoleVo> roles;
    private AgencyVo agency;


    public AgentVo (long id){
        this.id=id;
    }
}


