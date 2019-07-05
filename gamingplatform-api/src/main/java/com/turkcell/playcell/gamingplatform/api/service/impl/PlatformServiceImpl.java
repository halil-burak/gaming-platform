package com.turkcell.playcell.gamingplatform.api.service.impl;

import com.turkcell.playcell.gamingplatform.api.dto.PlatformDTO;
import com.turkcell.playcell.gamingplatform.api.dto.SliderDTO;
import com.turkcell.playcell.gamingplatform.api.response.PlatformResponse;
import com.turkcell.playcell.gamingplatform.api.service.PlatformService;
import com.turkcell.playcell.gamingplatform.common.entity.Platform;
import com.turkcell.playcell.gamingplatform.common.entity.PlatformTranslation;
import com.turkcell.playcell.gamingplatform.common.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;

    @Override
    public PlatformResponse getPlatformInfoByPlatformName(String platformName) {
        Platform platform = platformRepository.findByNameIgnoreCase(platformName);
        List<SliderDTO> sliders = null;
        for (PlatformTranslation pt : platform.getPlatformTranslations()) {
            sliders = pt.getSliderImages().stream().map(sliderImage -> {
                SliderDTO sliderDTO = new SliderDTO();
                sliderDTO.setExternalUrl(sliderImage.getExternalUrl());
                sliderDTO.setUrl(sliderImage.getImage().getCdnUrl().concat(sliderImage.getImage().getPath()));
                return sliderDTO;
            }).collect(Collectors.toList());

        }
        return PlatformResponse.builder()
                .platform(PlatformDTO.builder()
                        .logo((platform.getLogo() != null) ? platform.getLogo().getCdnUrl().concat(platform.getLogo().getPath()) : null)
                        .name(platform.getName())
                        .sliders(sliders)
                        .build())
                .build();
    }
}
