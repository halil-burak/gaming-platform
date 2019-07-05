package com.turkcell.playcell.gamingplatform.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GameDetailDTO {
    private String name;
    private String label;
    private String slug;
    private String description;
    private List<ImageDTO> images = new ArrayList<>();
    private String platform;
    private List<String> packages;
    private List<Long> categories;
}
