package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PlatformDTO {
    private String name;
    private List<SliderDTO> sliders;
    private String logo;
}
