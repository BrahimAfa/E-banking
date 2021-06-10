package com.ensas.ebanking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SETTINGS")
@Data
@ToString
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String key_data;
    private String data;
    private String type; //Boolea, String ,int...
    private Date createdAt;
}
