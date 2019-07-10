package com.turkcell.playcell.gamingplatform.cms.dto.game;


import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformDTO;

import java.time.Instant;

public class GameDetailPublishDTO {

    private Long id;

    private String gameName;

    private PlatformDTO platform;

    private Instant publishDatetime;

    private Instant unpublishDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public PlatformDTO getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformDTO platform) {
        this.platform = platform;
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
