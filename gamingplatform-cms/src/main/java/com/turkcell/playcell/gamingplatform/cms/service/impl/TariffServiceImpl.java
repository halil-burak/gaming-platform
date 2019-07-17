package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.dto.tariff.TariffCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.tariff.TariffDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.NotFoundException;
import com.turkcell.playcell.gamingplatform.cms.service.TariffService;
import com.turkcell.playcell.gamingplatform.common.entity.Tariff;
import com.turkcell.playcell.gamingplatform.common.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

    private final TariffRepository tariffRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long saveTariff(TariffCreateDTO tariffCreateDTO) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Tariff tariff = modelMapper.map(tariffCreateDTO, Tariff.class);
        return tariffRepository.save(tariff).getId();
    }

    @Override
    public void updateTariff(Long id, TariffCreateDTO tariffCreateDTO) {
        Tariff tariff = tariffRepository.findById(id).orElseThrow(() -> new NotFoundException(Tariff.class, id));
        tariff.setName(tariffCreateDTO.getName());
        tariff.setGrade(tariffCreateDTO.getGrade());
        tariff.setLabel(tariffCreateDTO.getLabel());
        tariff.setOfferId(tariffCreateDTO.getOfferId());
        tariff.setPackageName(tariffCreateDTO.getPackageName());
        tariff.setProvisioningId(tariffCreateDTO.getProvisioningId());
        tariffRepository.save(tariff);
    }

    @Override
    public void deleteTariff(Long id) {
        tariffRepository.deleteById(id);
    }

    @Override
    public List<TariffDTO> getTariffs() {
        List<Tariff> tariffs = tariffRepository.findAll();
        return tariffs.stream().map( t-> modelMapper.map(t, TariffDTO.class)).collect(Collectors.toList());
    }
}
