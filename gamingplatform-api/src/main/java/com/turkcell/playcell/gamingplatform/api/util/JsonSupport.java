package com.turkcell.playcell.gamingplatform.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public interface JsonSupport {

    default <T> String asJson(ObjectMapper objectMapper, T in) {
        try{
            return objectMapper.writeValueAsString(in);
        }catch(JsonProcessingException j){
            throw new RuntimeException(j);
        }
    }

    default Object fromJson(ObjectMapper objectMapper, Class clazz, String in) {
        try{
            return objectMapper.readValue(in, TypeFactory.defaultInstance().constructType(clazz));
        }catch(Exception j){
            throw new RuntimeException(j);
        }
    }
    
}
