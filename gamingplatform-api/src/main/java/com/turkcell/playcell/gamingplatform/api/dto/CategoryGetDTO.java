package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryGetDTO {
    private Long id;
    private String name;
}
