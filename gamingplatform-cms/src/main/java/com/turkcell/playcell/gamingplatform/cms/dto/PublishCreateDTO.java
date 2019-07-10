package com.turkcell.playcell.gamingplatform.cms.dto;

import java.time.Instant;

public class PublishCreateDTO {

    private Instant publishTime;

    private Instant publishEndTime;

    public Instant getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Instant publishTime) {
        this.publishTime = publishTime;
    }

    public Instant getPublishEndTime() {
        return publishEndTime;
    }

    public void setPublishEndTime(Instant publishEndTime) {
        this.publishEndTime = publishEndTime;
    }
}
