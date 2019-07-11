package com.turkcell.playcell.gamingplatform.cms.dto.language;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LanguageDTO {

    private Long id;

    private String name;

    private String shortName;

    List<Long> platforms;

}
