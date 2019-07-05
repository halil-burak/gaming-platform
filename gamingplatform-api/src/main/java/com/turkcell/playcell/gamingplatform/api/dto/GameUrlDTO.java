package com.turkcell.playcell.gamingplatform.api.dto;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GameUrlDTO implements Serializable {
	private static final long serialVersionUID = -3138966567084849583L;
	private Long id;
    private String url;
    private String defaultSlug;
}
