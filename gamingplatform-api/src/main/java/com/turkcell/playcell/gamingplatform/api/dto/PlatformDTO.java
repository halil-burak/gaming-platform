package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PlatformDTO {
    private Long id;
    private String name;
}
