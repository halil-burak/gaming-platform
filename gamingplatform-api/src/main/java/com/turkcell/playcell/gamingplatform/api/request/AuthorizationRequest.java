package com.turkcell.playcell.gamingplatform.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRequest {
	
	@JsonProperty("accesscode")
    private String accesscode;

    @JsonProperty("redirecturl")
    private String redirecturl;
}
