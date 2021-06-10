package com.ensas.ebanking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
@Data
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID",unique = true,nullable = false)
    private long userId;

    @Column(name = "FIRST_NAME")
    @NotEmpty(message = "*Please provide your last name")
    @Length(min = 5, message = "*Your Last Name must have at least 5 characters")
    private String firstname;

    @Column(name = "LAST_NAME")
    @NotEmpty(message = "*Please provide your last name")
    @Length(min = 5, message = "*Your First Name must have at least 5 characters")
    private String lastname;

    @Column(name = "IS_ACTIVE")
    private boolean isActive = true;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    @Column(name = "EMAIL")
    private String email;

    @NotEmpty(message = "*Please provide CIN")
    @Column(name = "CIN")
    private String CIN;

    @Column(name = "USERNAME")
    private String username;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "ID"), // User
            inverseJoinColumns = @JoinColumn(name = "role_id") // role
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private List<Benificier> benificiers;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private List<Account> accounts;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agnet_id", nullable = false)
    private  User responsableAgent;

    public User(long id){
        this.userId=id;
    }
}
