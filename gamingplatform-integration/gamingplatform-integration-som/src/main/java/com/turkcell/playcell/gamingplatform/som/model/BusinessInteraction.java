package com.turkcell.playcell.gamingplatform.som.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessInteraction {
	
    private BusinessInteractionError businessInteractionError;

    private BusinessInteractionApproval businessInteractionApproval;

    private BusinessInteractionInfo businessInteractionInfo;

}
