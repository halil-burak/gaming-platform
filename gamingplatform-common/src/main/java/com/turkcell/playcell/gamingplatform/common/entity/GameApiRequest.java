package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GameApiRequest {

    private Long platformId;
    private Long tariffId;
    private String platformName;
    private String tariffName;
    
}
