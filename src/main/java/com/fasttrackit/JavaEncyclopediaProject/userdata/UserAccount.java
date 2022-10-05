package com.fasttrackit.JavaEncyclopediaProject.userdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
