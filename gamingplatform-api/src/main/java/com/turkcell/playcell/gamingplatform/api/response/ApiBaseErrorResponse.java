package com.turkcell.playcell.gamingplatform.api.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.turkcell.playcell.gamingplatform.api.enumtypes.ResponseCodeStrings;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiBaseErrorResponse implements Serializable {
	
    private static final long serialVersionUID = -376166269791768845L;

    @JsonProperty("meta")
    private MetaData meta = new MetaData();
    
    public static ApiBaseErrorResponse createErrorResponse(String message)
    {
    	ApiBaseErrorResponse resp = new ApiBaseErrorResponse();
        resp.meta = new MetaData(false, ResponseCodeStrings.EXCEPTION_ERROR, message);
        return resp;
    }
    

}
