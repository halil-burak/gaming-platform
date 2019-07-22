package com.turkcell.playcell.gamingplatform.cms.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetDTO {

    private Long id;

    private String username;

    private Integer role;

    private Boolean isactive;

}
