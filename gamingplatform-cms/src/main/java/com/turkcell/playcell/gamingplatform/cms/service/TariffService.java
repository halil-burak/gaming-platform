package com.turkcell.playcell.gamingplatform.cms.service;

import com.turkcell.playcell.gamingplatform.cms.dto.tariff.TariffCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.tariff.TariffDTO;

import java.util.List;

public interface TariffService {
    Long saveTariff(TariffCreateDTO tariffCreateDTO);

    void updateTariff(Long id, TariffCreateDTO tariffCreateDTO);

    void deleteTariff(Long id);

    List<TariffDTO> getTariffs();
}
