package com.turkcell.playcell.gamingplatform.cms.service;

import com.turkcell.playcell.gamingplatform.cms.dto.PageDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.*;

import java.util.List;

public interface GameService {

    Long saveGame(GameCreateDTO gameDTO);

    List<Long> updateGame(Long id, GameUpdateDTO gameDTO);

    GameGetOneDTO getGame(Long id);

    //List<GameGetDTO> getGames();

    List<GameDTO> getGamesByPlatform(Long platformId);

    List<GameDTO> getGamesByPlatformAndCategory(Long platformId, Long categoryId);

    void deleteGame(Long id);

    PageDTO<GameGetDTO> getGames(String search, Integer pageIndex, Integer size, List<Long> platforms, boolean onlyPublished);

    //void publishGame(Long gameId, Long platformId, PublishCreateDTO publishCreateDTO);
}
