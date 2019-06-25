package com.turkcell.playcell.gamingplatform.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GameDetailDto {
    private String name;
    private String label;
    private String slug;
    private String description;
    private List<ImageDto> images = new ArrayList<>();
    private String platform;
    private List<String> packages;
    private List<Long> categories;
}
