package com.turkcell.playcell.gamingplatform.cms.dto.ftpAccount;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FtpAccountGetDTO {

    private Long id;

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

    private String path;

    @NotNull
    @NotEmpty
    private String cdnBaseUrl;

}
