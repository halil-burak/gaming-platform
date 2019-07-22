package com.turkcell.playcell.gamingplatform.cms.dto.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
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

    @JsonProperty(value="isActive")
    public boolean isActive() {
        return isActive;
    }

}
