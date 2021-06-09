package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BenificierVo {
    private long id;
    private String accountNum;
    private String firstname;
    private String lastName;
    private String tele;
    private String email;
    private ClientVo client;

}
