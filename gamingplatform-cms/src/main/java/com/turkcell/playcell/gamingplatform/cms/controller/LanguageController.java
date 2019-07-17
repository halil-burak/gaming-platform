package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.language.LanguageCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.language.LanguageDTO;
import com.turkcell.playcell.gamingplatform.cms.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping("")
    public Long saveLanguage(@RequestBody @Valid LanguageCreateDTO languageCreateDTO) {
        return languageService.saveLanguage(languageCreateDTO);
    }

    @PutMapping("/{id}")
    public void updateLanguage(@PathVariable(name = "id")Long id, @RequestBody @Valid LanguageCreateDTO languageCreateDTO) {
        languageService.updateLanguage(id, languageCreateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable(name = "id") Long id) {
        languageService.deleteLanguage(id);
    }

    @GetMapping("")
    public List<LanguageDTO> getLanguages() {
        return languageService.getLanguages();
    }

    @GetMapping("/platforms/{platformId}")
    public List<LanguageDTO> getLanguagesByPlatform(@PathVariable(name = "platformId") Long platformId) {
        return languageService.getLanguagesByPlatform(platformId);
    }

}
