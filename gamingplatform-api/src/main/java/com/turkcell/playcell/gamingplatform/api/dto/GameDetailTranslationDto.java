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
public class GameDetailTranslationDto {
	 private String name;
	 private String gameSlug;
	 private String description;
	 private String language;
	 private List<ImageDto> images = new ArrayList<>();
}
