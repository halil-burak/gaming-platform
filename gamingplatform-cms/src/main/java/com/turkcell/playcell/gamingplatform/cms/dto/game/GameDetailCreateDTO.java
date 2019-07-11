package com.turkcell.playcell.gamingplatform.cms.dto.game;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GameDetailCreateDTO extends GameDetailUploadDTO {

    @NotNull
    private Long gameId;

}
