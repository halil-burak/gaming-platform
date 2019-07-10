package com.turkcell.playcell.gamingplatform.cms.dto.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class GameDetailLightDTO {

    private Long id;

    private Long platformId;

    private boolean isActive;

    private Instant publishDatetime;

    private Instant unpublishDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    @JsonProperty(value="isActive")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
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
}
