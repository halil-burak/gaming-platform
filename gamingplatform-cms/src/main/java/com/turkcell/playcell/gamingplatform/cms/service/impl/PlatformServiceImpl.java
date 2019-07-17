package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformTranslationGetDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.slider_logo.LogoDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.slider_logo.SliderGetDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.FileDeleteException;
import com.turkcell.playcell.gamingplatform.cms.exception.NotFoundException;
import com.turkcell.playcell.gamingplatform.cms.service.FileUploadService;
import com.turkcell.playcell.gamingplatform.cms.service.PlatformService;
import com.turkcell.playcell.gamingplatform.common.entity.*;
import com.turkcell.playcell.gamingplatform.common.repository.*;
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
public class PlatformServiceImpl implements PlatformService {

    private final ModelMapper modelMapper;

    private final PlatformRepository platformRepository;

    private final LanguageRepository languageRepository;

    private final ImageRepository imageRepository;

    private final PlatformTranslationRepository platformTranslationRepository;

    private final SliderImageRepository sliderImageRepository;

    private final FileUploadService fileUploadService;

    @Override
    public Long savePlatform(PlatformCreateDTO platformCreateDTO) {
        Platform platform = new Platform();
        platform.setName(platformCreateDTO.getName());
        platform.setFtpAccount(new FtpAccount(platformCreateDTO.getFtpAccountId()));

        platform.getLanguages().addAll(platformCreateDTO.getTranslations().stream().map(platformTranslationDTO -> new Language(platformTranslationDTO.getLanguageId())).collect(Collectors.toList()));

        platform.getPlatformTranslations().addAll(platformCreateDTO.getTranslations().stream().map(platformTranslationDTO -> {
            PlatformTranslation platformTranslation = new PlatformTranslation();
            platformTranslation.setPlatform(platform);
            platformTranslation.setLanguage(new Language(platformTranslationDTO.getLanguageId()));
            platformTranslation.getSliderImages().addAll(platformTranslationDTO.getSliderImages().stream().map(sliderDTO -> {
                SliderImage sliderImage = new SliderImage();
                sliderImage.setExternalUrl(sliderDTO.getExternalUrl());
                sliderImage.setImage(imageRepository.findById(sliderDTO.getId()).get());
                sliderImage.setPlatformTranslation(platformTranslation);
                return sliderImage;
            }).collect(Collectors.toList()));
            return platformTranslation;
        }).collect(Collectors.toList()));

        platform.setLogo(imageRepository.findById(platformCreateDTO.getLogo()).get());
        return platformRepository.save(platform).getId();
    }

    @Override
    public void updatePlatform(Long id, PlatformCreateDTO platformCreateDTO) {
        Platform platform = platformRepository.findById(id).orElseThrow(()-> new NotFoundException(Platform.class, id));
        platform.setName(platformCreateDTO.getName());
        platform.setFtpAccount(new FtpAccount(platformCreateDTO.getFtpAccountId()));
        platform.getPlatformTranslations().clear();

        platform.getLanguages().clear();
        platform.getLanguages().addAll(platformCreateDTO.getTranslations().stream().map(platformTranslationDTO -> new Language(platformTranslationDTO.getLanguageId())).collect(Collectors.toList()));

        platform.getPlatformTranslations().addAll(platformCreateDTO.getTranslations().stream().map(platformTranslationDTO -> {
            PlatformTranslation platformTranslation;
            if (platformTranslationDTO.getId() == null) {
                platformTranslation = new PlatformTranslation();
                platformTranslation.setPlatform(platform);
            } else {
                platformTranslation = platformTranslationRepository.findById(platformTranslationDTO.getId()).get();
            }

            platformTranslation.setLanguage(new Language(platformTranslationDTO.getLanguageId()));
            //platformTranslation.getSliderImages().clear();
            platformTranslation.getSliderImages().addAll(platformTranslationDTO.getSliderImages().stream().map(sliderDTO -> {

                SliderImage sliderImage;
                if (sliderDTO.getId() == null) {
                    sliderImage = new SliderImage();
                    sliderImage.setPlatformTranslation(platformTranslation);
                } else {
                    sliderImage = sliderImageRepository.findByImageId(sliderDTO.getId());
                    if (sliderImage == null) {
                        sliderImage = new SliderImage();
                        sliderImage.setPlatformTranslation(platformTranslation);
                        Image image = imageRepository.findById(sliderDTO.getId()).orElseThrow(() -> new NotFoundException(Image.class, id));
                        sliderImage.setImage(image);
                    }
                }
                sliderImage.setExternalUrl(sliderDTO.getExternalUrl());

                return sliderImage;
            }).collect(Collectors.toList()));
            return platformTranslation;
        }).collect(Collectors.toList()));

        if (platform.getLogo() != null && platformCreateDTO.getLogo() == null) {
            platform.setLogo(null);
        } else if (platform.getLogo() == null && platformCreateDTO.getLogo() != null) {
            platform.setLogo(imageRepository.findById(platformCreateDTO.getLogo()).orElseThrow(() -> new NotFoundException(Image.class, id)));
        }
        platformRepository.save(platform);
    }

    @Override
    public void deletePlatform(Long id) {
        platformRepository.deleteById(id);
    }

    @Override
    public List<PlatformDTO> getPlatforms() {
        List<Platform> platforms = platformRepository.findAll();
        return platforms.stream().map(platform -> {
            PlatformDTO platformDTO= new PlatformDTO();
            platformDTO.setId(platform.getId());
            platformDTO.setName(platform.getName());
            platformDTO.setFtpAccountId(platform.getFtpAccount().getId());
            platformDTO.setTranslations(platform.getPlatformTranslations().stream().map(platformTranslation -> {
                PlatformTranslationGetDTO platformTranslationDTO = new PlatformTranslationGetDTO();
                platformTranslationDTO.setLanguageId(platformTranslation.getLanguage().getId());
                platformTranslationDTO.setSliderImages(platformTranslation.getSliderImages().stream().map(sliderImage -> {
                    SliderGetDTO sliderDTO = new SliderGetDTO();
                    sliderDTO.setExternalUrl(sliderImage.getExternalUrl());
                    sliderDTO.setUrl(sliderImage.getImage().getCdnUrl().concat(sliderImage.getImage().getPath()));
                    sliderDTO.setId(sliderImage.getImage().getId());
                    return sliderDTO;
                }).collect(Collectors.toList()));
                return platformTranslationDTO;
            }).collect(Collectors.toList()));

            if (platform.getLogo() != null) {
                LogoDTO logo = new LogoDTO();
                logo.setUrl(platform.getLogo().getCdnUrl().concat(platform.getLogo().getPath()));
                logo.setId(platform.getLogo().getId());
                platformDTO.setLogo(logo);
            }
            return platformDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteLogoFromPlatform(Long id, Long logoImageId) throws FileDeleteException {
        Platform platform = platformRepository.findById(id).orElseThrow(() -> new NotFoundException(Platform.class, id));
        platform.setLogo(null);
        fileUploadService.deleteFile(logoImageId);
    }

}
