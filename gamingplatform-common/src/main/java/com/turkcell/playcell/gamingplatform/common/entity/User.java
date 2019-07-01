package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CMS_USER")
@NoArgsConstructor
@Data
public class User extends BaseEntity implements Serializable {

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password ;

    @Column(name = "ROLE")
    private Integer role;

    @Column(name = "ACTIVATION")
    private Boolean isactive;

    @Column(name = "TOKEN")
    private String token;
}



