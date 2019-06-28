package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GameFile extends BaseEntity {

    private String cdnUrl;

    private String path;

    @ManyToOne
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private FtpAccount ftpAccount;

    public GameFile() {

    }

    public GameFile(Long id) {
        super.setId(id);
    }


    public String getCdnUrl() {
        return cdnUrl;
    }

    public void setCdnUrl(String cdnUrl) {
        this.cdnUrl = cdnUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FtpAccount getFtpAccount() {
        return ftpAccount;
    }

    public void setFtpAccount(FtpAccount ftpAccount) {
        this.ftpAccount = ftpAccount;
    }
}
