package com.turkcell.playcell.gamingplatform.api.response;

import com.turkcell.playcell.gamingplatform.api.enumtypes.ResponseCodeStrings;

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
public class DataResponse<T> {
	
    private MetaData meta;
    private T data;
    
    public static <T> DataResponse<T> createSuccessTokenResponse(T data, String message)
    {
        DataResponse<T> resp = new DataResponse<T>();
        resp.data = data;
        resp.meta = new MetaData(true, ResponseCodeStrings.SUCCESS, message);
        return resp;
    }

    public static <T> DataResponse<T> createResponse(T data, Boolean success, ResponseCodeStrings returnCode, String message) 
    {
        DataResponse<T> resp = new DataResponse<T>();
        resp.data = data;
        MetaData meta = MetaData.builder().success(success).code(returnCode).message(message).build();
        resp.setMeta(meta);
        return resp;
    }
}
