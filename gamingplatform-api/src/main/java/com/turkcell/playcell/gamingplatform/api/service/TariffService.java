package com.turkcell.playcell.gamingplatform.api.service;

import com.turkcell.playcell.gamingplatform.common.entity.Tariff;

public interface TariffService {
	
	Tariff findByName(String name);
}