package com.turkcell.playcell.gamingplatform.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GameDetailTranslationDTO {
	 private String name;
	 private String gameSlug;
	 private String description;
	 private String language;
	 private List<ImageDTO> images = new ArrayList<>();
}
