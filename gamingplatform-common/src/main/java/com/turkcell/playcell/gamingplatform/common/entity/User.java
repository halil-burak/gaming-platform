package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CMS_USER")
public class User extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4837571984922071995L;

	@Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "ROLE")
    private Integer role;

    @Column(name = "TOKEN")
    private String token;

    public User(String username){
        this.username = username;
    }
}



