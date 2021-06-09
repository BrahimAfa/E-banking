package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentVo {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private Role role;
}
