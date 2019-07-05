package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryTranslationDTO {
    private String name;
    private String language;
    private String slug;
}
