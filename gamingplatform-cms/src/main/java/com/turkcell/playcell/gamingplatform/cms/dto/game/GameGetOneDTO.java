package com.turkcell.playcell.gamingplatform.cms.dto.game;

import com.turkcell.playcell.gamingplatform.cms.dto.GameFileDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameGetOneDTO {
    private Long id;

    private String name;

    private String url;

    private GameFileDTO gameFileDTO;

    private boolean hideOnCategories;

    private List<GameDetailLightDTO> gameDetails;

    private int score;

    private Long ftpAccountId;

}
