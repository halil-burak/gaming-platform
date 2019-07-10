package com.turkcell.playcell.gamingplatform.cms.service;

import com.turkcell.playcell.gamingplatform.cms.dto.language.LanguageCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.language.LanguageDTO;

import java.util.List;

public interface LanguageService {

    Long saveLanguage(LanguageCreateDTO languageCreateDTO);

    void updateLanguage(Long id, LanguageCreateDTO languageCreateDTO);

    void deleteLanguage(Long id);

    List<LanguageDTO> getLanguages();

    List<LanguageDTO> getLanguagesByPlatform(Long platformId);

}
