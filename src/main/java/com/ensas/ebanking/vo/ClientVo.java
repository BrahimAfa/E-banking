package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientVo {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private Set<RoleVo> roles;
    private String password; //ignore this in serializing
    contractStatus
    /contract/clientId {status:"tirminer"}
}
