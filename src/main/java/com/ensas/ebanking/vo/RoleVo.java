package com.ensas.ebanking.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {
    private long id;
    private String name;
    private boolean isActive;
}
