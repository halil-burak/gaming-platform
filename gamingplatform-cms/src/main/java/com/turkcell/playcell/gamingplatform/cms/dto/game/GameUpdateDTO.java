package com.turkcell.playcell.gamingplatform.cms.dto.game;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class GameUpdateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String url;

    private Long gameFileId;

    @NotNull
    private boolean hideOnCategories;

    @NotNull
    private List<Long> platforms;

    private int score;

}
