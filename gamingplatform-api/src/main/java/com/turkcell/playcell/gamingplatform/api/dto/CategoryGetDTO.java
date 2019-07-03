package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryGetDTO {
    private Long id;
    private String name;
}
