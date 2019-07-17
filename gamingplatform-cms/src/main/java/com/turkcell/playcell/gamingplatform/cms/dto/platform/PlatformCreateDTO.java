package com.turkcell.playcell.gamingplatform.cms.dto.platform;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class PlatformCreateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Long ftpAccountId;

    @NotNull
    private List<PlatformTranslationDTO> translations;

    private Long logo;

}
