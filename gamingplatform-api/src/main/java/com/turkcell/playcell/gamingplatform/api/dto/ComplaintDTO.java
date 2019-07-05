package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ComplaintDTO implements Serializable {
    private String agent;
    private String complaint;
}
