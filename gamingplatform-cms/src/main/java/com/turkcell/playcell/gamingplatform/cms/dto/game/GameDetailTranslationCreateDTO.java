package com.turkcell.playcell.gamingplatform.cms.dto.game;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class GameDetailTranslationCreateDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private List<String> gameSlugs;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private List<Long> images;

    @NotNull
    private Long languageId;

}

