package com.turkcell.playcell.gamingplatform.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GameUrlDto implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3138966567084849583L;
	private Long id;
    private String url;
    private String defaultSlug;
    
}
