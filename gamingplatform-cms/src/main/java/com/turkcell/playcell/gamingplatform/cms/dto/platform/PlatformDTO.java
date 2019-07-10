package com.turkcell.playcell.gamingplatform.cms.dto.platform;

import com.turkcell.playcell.gamingplatform.cms.dto.slider_logo.LogoDTO;

import java.util.List;

public class PlatformDTO {

    private Long id;

    private String name;

    private Long ftpAccountId;

    private List<PlatformTranslationGetDTO> translations;

    private LogoDTO logo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFtpAccountId() {
        return ftpAccountId;
    }

    public void setFtpAccountId(Long ftpAccountId) {
        this.ftpAccountId = ftpAccountId;
    }

    public List<PlatformTranslationGetDTO> getTranslations() {
        return translations;
    }

    public void setTranslations(List<PlatformTranslationGetDTO> translations) {
        this.translations = translations;
    }

    public LogoDTO getLogo() {
        return logo;
    }

    public void setLogo(LogoDTO logo) {
        this.logo = logo;
    }
    
}
