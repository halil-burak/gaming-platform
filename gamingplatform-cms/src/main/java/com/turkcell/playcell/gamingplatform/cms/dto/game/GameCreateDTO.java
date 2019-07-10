package com.turkcell.playcell.gamingplatform.cms.dto.game;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

public class GameCreateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String url;

    @NotNull
    private boolean hideOnCategories;

    private int score;

    private Long gameFileId;

    @NotNull
    private List<Long> tariffs;

    @NotNull
    private List<Long> categories;

    @NotNull
    private List<Long> platforms;

    private List<GameDetailTranslationCreateDTO> translations;

    private Instant publishDatetime;

    private Instant unpublishDatetime;

    private Long ftpAccountId;

    public Long getFtpAccountId() {
        return ftpAccountId;
    }

    public void setFtpAccountId(Long ftpAccountId) {
        this.ftpAccountId = ftpAccountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getGameFileId() {
        return gameFileId;
    }

    public void setGameFileId(Long gameFileId) {
        this.gameFileId = gameFileId;
    }

    public boolean isHideOnCategories() {
        return hideOnCategories;
    }

    public void setHideOnCategories(boolean hideOnCategories) {
        this.hideOnCategories = hideOnCategories;
    }

    public List<Long> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Long> tariffs) {
        this.tariffs = tariffs;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public List<Long> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Long> platforms) {
        this.platforms = platforms;
    }

    public List<GameDetailTranslationCreateDTO> getTranslations() {
        return translations;
    }

    public void setTranslations(List<GameDetailTranslationCreateDTO> translations) {
        this.translations = translations;
    }

    public Instant getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(Instant publishDatetime) {
        this.publishDatetime = publishDatetime;
    }

    public Instant getUnpublishDatetime() {
        return unpublishDatetime;
    }

    public void setUnpublishDatetime(Instant unpublishDatetime) {
        this.unpublishDatetime = unpublishDatetime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
