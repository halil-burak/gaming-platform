package com.turkcell.playcell.gamingplatform.cms.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Getter
@Setter
public class UserCreateDTO {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private char [] password;

    @NotNull
    private Integer role;

    @NotNull
    private Boolean isactive;

    @Override
    public void finalize() {
        Arrays.fill(password, '0');
    }
}
