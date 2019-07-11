package com.turkcell.playcell.gamingplatform.cms.dto.platform;

import com.turkcell.playcell.gamingplatform.cms.dto.slider_logo.SliderGetDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlatformTranslationGetDTO {
    private Long id;

    private Long languageId;

    private List<SliderGetDTO> sliderImages = new ArrayList<>();

}
