package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.dto.language.LanguageCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.language.LanguageDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.NotFoundException;
import com.turkcell.playcell.gamingplatform.cms.service.LanguageService;
import com.turkcell.playcell.gamingplatform.common.entity.Language;
import com.turkcell.playcell.gamingplatform.common.entity.Platform;
import com.turkcell.playcell.gamingplatform.common.repository.LanguageRepository;
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
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long saveLanguage(LanguageCreateDTO languageCreateDTO) {
        Language language = modelMapper.map(languageCreateDTO, Language.class);
        return languageRepository.save(language).getId();
    }

    @Override
    public void updateLanguage(Long id, LanguageCreateDTO languageCreateDTO) {
        Language language = languageRepository.findById(id).orElseThrow(()-> new NotFoundException(Language.class, id));
        language.setName(languageCreateDTO.getName());
        language.setShortName(languageCreateDTO.getShortName());
    }

    @Override
    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }

    @Override
    public List<LanguageDTO> getLanguages() {
        List<Language> languages = languageRepository.findAll();
        return languages.stream().map(l -> {
            LanguageDTO languageDTO = new LanguageDTO();
            languageDTO.setId(l.getId());
            languageDTO.setName(l.getName());
            languageDTO.setShortName(l.getShortName());
            languageDTO.setPlatforms(l.getPlatforms().stream().map(Platform::getId).collect(Collectors.toList()));
            return languageDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<LanguageDTO> getLanguagesByPlatform(Long platformId) {
        List<Language> languagesByPlatform = languageRepository.findDistinctByPlatformsId(platformId);
        return languagesByPlatform.stream()
                .map(language -> {
                    LanguageDTO languageDTO = new LanguageDTO();
                    languageDTO.setId(language.getId());
                    languageDTO.setName(language.getName());
                    languageDTO.setShortName(language.getShortName());
                    languageDTO.setPlatforms(language.getPlatforms().stream().map(Platform::getId).collect(Collectors.toList()));
                    return languageDTO;
                })
                .collect(Collectors.toList());
    }
}
