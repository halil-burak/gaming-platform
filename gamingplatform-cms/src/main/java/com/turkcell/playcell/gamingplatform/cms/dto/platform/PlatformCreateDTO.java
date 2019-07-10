package com.turkcell.playcell.gamingplatform.cms.dto.platform;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PlatformCreateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Long ftpAccountId;

    @NotNull
    private List<PlatformTranslationDTO> translations;

    private Long logo;

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

    public List<PlatformTranslationDTO> getTranslations() {
        return translations;
    }

    public void setTranslations(List<PlatformTranslationDTO> translations) {
        this.translations = translations;
    }

    public Long getLogo() {
        return logo;
    }

    public void setLogo(Long logo) {
        this.logo = logo;
    }
}
