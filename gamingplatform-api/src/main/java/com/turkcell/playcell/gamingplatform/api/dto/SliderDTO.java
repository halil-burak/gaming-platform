package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SliderDTO {
    private String url;
    private String externalUrl;
}
