package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Image extends BaseEntity {

    private Long sizeId;

    private String cdnUrl;

    private String path;

    @ManyToOne
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private FtpAccount ftpAccount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GAME_DETAIL_TRANSLATION_IMAGE",
            joinColumns = @JoinColumn(name = "IMAGE_ID"),
            inverseJoinColumns = @JoinColumn(name = "GAME_DETAIL_TRANSLATION_ID"))
    private List<GameDetailTranslation>  gameDetailTranslations = new ArrayList<>();

    public Image() {
    }

    public Image(Long sizeId, String cdnUrl, String path, FtpAccount ftpAccount) {
        this.sizeId = sizeId;
        this.cdnUrl = cdnUrl;
        this.path = path;
        this.ftpAccount = ftpAccount;
    }


    public Image(Long id) {
        super.setId(id);
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
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

    public List<GameDetailTranslation> getGameDetailTranslations() {
        return gameDetailTranslations;
    }

    public void setGameDetailTranslations(List<GameDetailTranslation> gameDetailTranslations) {
        this.gameDetailTranslations = gameDetailTranslations;
    }

    public FtpAccount getFtpAccount() {
        return ftpAccount;
    }

    public void setFtpAccount(FtpAccount ftpAccount) {
        this.ftpAccount = ftpAccount;
    }

    @Override
    public String toString() {
        return "Image{" +
                "sizeId=" + sizeId +
                ", cdnUrl='" + cdnUrl + '\'' +
                ", path='" + path + '\'' +
                ", ftpAccountId=" + ftpAccount.getId() +
                '}';
    }
}
