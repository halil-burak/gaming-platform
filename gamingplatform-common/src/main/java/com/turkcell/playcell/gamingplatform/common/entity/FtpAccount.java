package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class FtpAccount extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "HOST")
    private String host;

    @Column(name = "PORT")
    private int port;

    @Column(name = "FTP_USER")
    private String user;

    @Column(name = "PASSWORD")
    private char [] password;

    @Column(name = "PATH")
    private String path;

    @Column(name = "CDN_BASE_URL")
    private String cdnBaseUrl;

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private List<Platform> platforms = new ArrayList<>();

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private List<Image> images = new ArrayList<>();

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private List<GameFile> gameFiles = new ArrayList<>();

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private List<Game> games = new ArrayList<>();

    FtpAccount(Long id) {
        super.setId(id);
    }

    @Override
    public void finalize() {
        Arrays.fill(password, '0');
    }
}
