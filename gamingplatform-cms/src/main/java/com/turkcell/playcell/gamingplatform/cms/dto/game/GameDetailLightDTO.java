package com.turkcell.playcell.gamingplatform.cms.dto.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class GameDetailLightDTO {

    private Long id;

    private Long platformId;

    private boolean isActive;

    private Instant publishDatetime;

    private Instant unpublishDatetime;

    @JsonProperty(value="isActive")
    public boolean isActive() {
        return isActive;
    }
}
