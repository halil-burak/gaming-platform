package com.turkcell.playcell.gamingplatform.cms.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private boolean isSeries;

    @NotNull
    private boolean isTag;

    @NotNull
    private boolean isVisible;

    @NotNull
    private List<Long> platforms;

    @NotNull
    private List<CategoryIconDTO> icons;

    @Valid
    @NotNull
    private List<CategoryTranslationCreateDTO> translations;


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

    @JsonProperty(value="isSeries")
    public boolean isSeries() {
        return isSeries;
    }

    @JsonProperty(value="isTag")
    public boolean isTag() {
        return isTag;
    }

    public void setTag(boolean tag) {
        isTag = tag;
    }

    @JsonProperty(value="isVisible")
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setSeries(boolean isSeries) {
        this.isSeries = isSeries;
    }

    public List<Long> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Long> platforms) {
        this.platforms = platforms;
    }

    public List<CategoryTranslationCreateDTO> getTranslations() {
        return translations;
    }

    public void setTranslations(List<CategoryTranslationCreateDTO> translations) {
        this.translations = translations;
    }

    public List<CategoryIconDTO> getIcons() {
        return icons;
    }

    public void setIcons(List<CategoryIconDTO> icons) {
        this.icons = icons;
    }
}
