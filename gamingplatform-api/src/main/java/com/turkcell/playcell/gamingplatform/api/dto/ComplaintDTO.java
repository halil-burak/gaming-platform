package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ComplaintDTO implements Serializable {
    private String agent;
    private String complaint;
}
