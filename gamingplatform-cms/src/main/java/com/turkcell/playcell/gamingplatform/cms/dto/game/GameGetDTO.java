package com.turkcell.playcell.gamingplatform.cms.dto.game;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameGetDTO {

    private Long id;

    private String name;

    private int score;

    private List<GameDetailLightDTO> gameDetails;

}
