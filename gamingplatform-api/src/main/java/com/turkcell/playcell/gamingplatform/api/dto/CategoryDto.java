package com.turkcell.playcell.gamingplatform.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6530914915960430078L;
	private Long id;
    private String name;
    private String title;
    private String slug;
    private boolean isVisible;
}
