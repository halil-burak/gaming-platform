package com.turkcell.playcell.gamingplatform.cms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ComplaintDTO {

    private String gameName;

    private String platformName;

    private Instant date;

    private String agent;

    private String complaint;

}

