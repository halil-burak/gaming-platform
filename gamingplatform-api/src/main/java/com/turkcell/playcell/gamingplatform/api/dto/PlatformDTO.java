package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PlatformDTO {
    private String name;
    private List<SliderDTO> sliders;
    private String logo;
}
