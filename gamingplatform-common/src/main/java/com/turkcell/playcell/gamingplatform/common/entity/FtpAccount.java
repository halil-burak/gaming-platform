package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
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

    public FtpAccount() { }

    public FtpAccount(Long id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCdnBaseUrl() {
        return cdnBaseUrl;
    }

    public void setCdnBaseUrl(String cdnBaseUrl) {
        this.cdnBaseUrl = cdnBaseUrl;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<GameFile> getGameFiles() {
        return gameFiles;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public String toString() {
        return "FtpAccount{" +
                "name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", path='" + path + '\'' +
                ", cdnBaseUrl='" + cdnBaseUrl + '\'' +
                '}';
    }

    @Override
    public void finalize() {
        Arrays.fill(password, '0');
    }
}
