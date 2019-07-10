package com.turkcell.playcell.gamingplatform.cms.dto.game;

import javax.validation.constraints.NotNull;

public class GameDetailCreateDTO extends GameDetailUploadDTO{

    @NotNull
    private Long gameId;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
