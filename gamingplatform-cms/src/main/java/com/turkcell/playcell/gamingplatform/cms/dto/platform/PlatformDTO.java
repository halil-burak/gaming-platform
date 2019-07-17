package com.turkcell.playcell.gamingplatform.cms.dto.platform;

import com.turkcell.playcell.gamingplatform.cms.dto.slider_logo.LogoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlatformDTO {

    private Long id;

    private String name;

    private Long ftpAccountId;

    private List<PlatformTranslationGetDTO> translations;

    private LogoDTO logo;
    
}
