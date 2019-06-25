package com.turkcell.playcell.gamingplatform.api.service;

import com.turkcell.playcell.gamingplatform.api.dto.GameUrlDto;

public interface IGameService {
	
	GameResponse getGamesByPlatformAndLanguage(String platformName, String language);

    GameUrlDto getGame(String userTariff, String platformName, String slug, String language);

}
