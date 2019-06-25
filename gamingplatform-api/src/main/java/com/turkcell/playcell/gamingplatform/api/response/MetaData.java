package com.turkcell.playcell.gamingplatform.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.turkcell.playcell.gamingplatform.api.enumtypes.ResponseCodeStrings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaData {
	
	@JsonProperty("success")
    private Boolean success;

    @JsonProperty("returnCode")
    private ResponseCodeStrings code;

    @JsonProperty("message")
    private String message;

}
