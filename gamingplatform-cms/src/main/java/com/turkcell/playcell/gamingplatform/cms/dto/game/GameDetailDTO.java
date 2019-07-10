package com.turkcell.playcell.gamingplatform.cms.dto.game;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

public class GameDetailDTO {

    @NotNull
    private Long platformId;

    @NotNull
    private List<GameDetailTranslationDTO> gameDetailTranslations;

    @NotNull
    private List<Long> categories;

    @NotNull
    private List<Long> tariffs;

    private boolean isActive;

    private Instant publishDatetime;

    private Instant unpublishDatetime;

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public List<GameDetailTranslationDTO> getGameDetailTranslations() {
        return gameDetailTranslations;
    }

    public void setGameDetailTranslations(List<GameDetailTranslationDTO> gameDetailTranslations) {
        this.gameDetailTranslations = gameDetailTranslations;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public List<Long> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Long> tariffs) {
        this.tariffs = tariffs;
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

    @JsonProperty(value="isActive")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
