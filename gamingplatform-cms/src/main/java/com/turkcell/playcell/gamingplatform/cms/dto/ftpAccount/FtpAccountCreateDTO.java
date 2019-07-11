package com.turkcell.playcell.gamingplatform.cms.dto.ftpAccount;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Getter
@Setter
public class FtpAccountCreateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String host;

    private int port;

    @NotNull
    @NotEmpty
    private String user;

    @NotNull
    @NotEmpty
    private char [] password;

    private String path;

    @NotNull
    @NotEmpty
    private String cdnBaseUrl;

    @Override
    public void finalize() {
        Arrays.fill(password, '0');
    }
}
