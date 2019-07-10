package com.turkcell.playcell.gamingplatform.cms.dto.game;

import com.turkcell.playcell.gamingplatform.cms.dto.GameFileDTO;

import java.util.List;

public class GameGetOneDTO {
    private Long id;

    private String name;

    private String url;

    private GameFileDTO gameFileDTO;

    private boolean hideOnCategories;

    private List<GameDetailLightDTO> gameDetails;

    private int score;

    private Long ftpAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public GameFileDTO getGameFileDTO() {
        return gameFileDTO;
    }

    public void setGameFileDTO(GameFileDTO gameFileDTO) {
        this.gameFileDTO = gameFileDTO;
    }

    public boolean isHideOnCategories() {
        return hideOnCategories;
    }

    public void setHideOnCategories(boolean hideOnCategories) {
        this.hideOnCategories = hideOnCategories;
    }

    public List<GameDetailLightDTO> getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(List<GameDetailLightDTO> gameDetails) {
        this.gameDetails = gameDetails;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getFtpAccountId() {
        return ftpAccountId;
    }

    public void setFtpAccountId(Long ftpAccountId) {
        this.ftpAccountId = ftpAccountId;
    }
}
