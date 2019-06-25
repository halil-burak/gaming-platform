package com.turkcell.playcell.gamingplatform.api.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.turkcell.playcell.gamingplatform.common.entity.Tariff;
import com.turkcell.playcell.gamingplatform.common.repository.TariffRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TariffService implements ITariffService {
	
    private final TariffRepository tariffRepository;

    @Override
    public Tariff findByName(String name) {
        return tariffRepository.findByName(name);
    }
}
