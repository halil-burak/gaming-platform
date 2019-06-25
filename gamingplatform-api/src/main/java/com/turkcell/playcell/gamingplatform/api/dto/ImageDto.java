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
public class ImageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8923862715214435130L;
	private Long sizeId;
    private String url;
}
