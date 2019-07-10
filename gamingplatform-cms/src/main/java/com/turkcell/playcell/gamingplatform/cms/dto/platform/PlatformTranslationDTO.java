package com.turkcell.playcell.gamingplatform.cms.dto.platform;


import com.turkcell.playcell.gamingplatform.cms.dto.slider_logo.SliderDTO;

import java.util.ArrayList;
import java.util.List;

public class PlatformTranslationDTO {
    private Long id;

    private Long languageId;

    private List<SliderDTO> sliderImages = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public List<SliderDTO> getSliderImages() {
        return sliderImages;
    }

    public void setSliderImages(List<SliderDTO> sliderImages) {
        this.sliderImages = sliderImages;
    }
}
