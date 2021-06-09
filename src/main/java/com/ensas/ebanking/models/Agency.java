package com.ensas.ebanking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AGENCY")
@Data
@ToString
public class Agency {
    @Id
    private long id;
    private String name;
    private String adress;
    private String ville;
    private String tele;
    @OneToMany(mappedBy = "agency", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users; // could be clients or employees;
}
