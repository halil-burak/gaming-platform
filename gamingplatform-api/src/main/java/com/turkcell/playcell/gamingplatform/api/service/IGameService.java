package com.turkcell.playcell.gamingplatform.api.service;

import com.turkcell.playcell.gamingplatform.api.dto.GameUrlDTO;

public interface IGameService {
	
	GameResponse getGamesByPlatformAndLanguage(String platformName, String language);

    GameUrlDTO getGame(String userTariff, String platformName, String slug, String language);
}
