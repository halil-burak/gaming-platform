package com.turkcell.playcell.gamingplatform.cms.dto.game;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class GameUpdateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String url;

    private Long gameFileId;

    @NotNull
    private boolean hideOnCategories;

    @NotNull
    private List<Long> platforms;

    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getGameFileId() {
        return gameFileId;
    }

    public void setGameFileId(Long gameFileId) {
        this.gameFileId = gameFileId;
    }

    public boolean isHideOnCategories() {
        return hideOnCategories;
    }

    public void setHideOnCategories(boolean hideOnCategories) {
        this.hideOnCategories = hideOnCategories;
    }

    public List<Long> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Long> platforms) {
        this.platforms = platforms;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
