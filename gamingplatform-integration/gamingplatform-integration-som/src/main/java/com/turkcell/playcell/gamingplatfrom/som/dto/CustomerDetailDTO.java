package com.turkcell.playcell.gamingplatfrom.som.dto;

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
public class CustomerDetailDTO {

    private String 	msisdn;
    private Integer cpcmOfferId;
    private Integer provServiceId;
    private String 	oldMsisdn;
    
}
