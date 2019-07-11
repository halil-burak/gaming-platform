package com.turkcell.playcell.gamingplatform.cms.dto.game;


import com.turkcell.playcell.gamingplatform.cms.dto.ImageDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class GameDetailTranslationDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private List<String> gameSlugs;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private List<ImageDTO> images;

    @NotNull
    private Long languageId;

}
