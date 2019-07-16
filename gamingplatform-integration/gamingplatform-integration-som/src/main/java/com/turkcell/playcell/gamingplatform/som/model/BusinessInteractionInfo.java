package com.turkcell.playcell.gamingplatform.som.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class BusinessInteractionInfo {
	
    private String type;

    private String messageText;

}
