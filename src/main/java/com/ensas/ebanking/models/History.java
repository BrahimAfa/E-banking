package com.ensas.ebanking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

// SYstem HISTORY
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HISTORY")
@Data
@ToString
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String action; // "DELETED_USER_[INFO(id...)]"
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    private Date createdAt;
}
