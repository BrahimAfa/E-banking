package com.ensas.ebanking.vo;

import com.ensas.ebanking.models.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

// SYstem HISTORY
public class HistoryVo {
    private long id;
    private String action; // "DELETED_USER_[INFO(id...)]"
    private ClientVo user;
    private Date createdAt;
}
