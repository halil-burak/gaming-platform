package com.turkcell.playcell.gamingplatform.cms.service;

import com.turkcell.playcell.gamingplatform.cms.dto.PageDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.GameDetailCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.GameDetailDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.GameDetailPublishDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.game.GameDetailUploadDTO;

public interface GameDetailService {

    Long saveGameDetail(GameDetailCreateDTO gameDetailCreateDTO);

    void updateGameDetail(Long id, GameDetailUploadDTO gameDetailUploadDTO);

    GameDetailDTO getGameDetail(Long id);

    void deleteGameDetail(Long id);

    PageDTO<GameDetailPublishDTO> getGameDetails(String search, Integer pageIndex, Integer size);
}
