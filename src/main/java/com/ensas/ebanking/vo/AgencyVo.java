package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AgencyVo {
    private long id;
    private String name;
    private String adress;
    private String ville;
    private String tele;
    private List<ClientVo> users; // could be clients or employees;
}