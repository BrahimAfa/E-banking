package com.ensas.ebanking.vo;

import java.util.Date;

public class SettingsVo {
    private long id;
    private String key;
    private String value;
    private String type; //Boolea, String ,int...
    private Date createdAt;
    private AgencyVo agence;
}
