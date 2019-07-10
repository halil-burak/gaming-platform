package com.turkcell.playcell.gamingplatform.cms.dto.game;


import com.turkcell.playcell.gamingplatform.cms.dto.ImageDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class GameDetailTranslationDTO {

    private Long id;

    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private List<String> gameSlugs;

    @NotNull @NotEmpty
    private String description;

    @NotNull
    private List<ImageDTO> images;

    @NotNull
    private Long languageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public List<String> getGameSlugs() {
        return gameSlugs;
    }

    public void setGameSlugs(List<String> gameSlugs) {
        this.gameSlugs = gameSlugs;
    }
}
