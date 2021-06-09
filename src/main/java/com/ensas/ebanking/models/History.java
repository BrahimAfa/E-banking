package com.ensas.ebanking.models;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

// SYstem HISTORY
public class History {
    private long id;
    private String action; // "DELETED_USER_[INFO(id...)]"
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    private Date createdAt;
}
