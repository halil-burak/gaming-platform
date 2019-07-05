package com.turkcell.playcell.gamingplatform.api.dto;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ImageDTO implements Serializable {
	private static final long serialVersionUID = -8923862715214435130L;
	private Long sizeId;
    private String url;
}
