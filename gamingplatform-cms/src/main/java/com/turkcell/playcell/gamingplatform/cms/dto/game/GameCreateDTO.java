package com.turkcell.playcell.gamingplatform.cms.dto.game;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class GameCreateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
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

}
