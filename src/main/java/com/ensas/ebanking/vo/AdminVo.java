package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminVo {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private Set<RoleVo> roles;
}
