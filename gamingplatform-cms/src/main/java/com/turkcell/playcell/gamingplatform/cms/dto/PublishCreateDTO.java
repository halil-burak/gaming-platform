package com.turkcell.playcell.gamingplatform.cms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PublishCreateDTO {

    private Instant publishTime;

    private Instant publishEndTime;

}
