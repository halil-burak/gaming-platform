package com.turkcell.playcell.gamingplatform.cms.dto.game;


import com.turkcell.playcell.gamingplatform.cms.dto.platform.PlatformDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class GameDetailPublishDTO {

    private Long id;

    private String gameName;

    private PlatformDTO platform;

    private Instant publishDatetime;

    private Instant unpublishDatetime;
}
