package com.turkcell.playcell.gamingplatform.api.response;

import com.turkcell.playcell.gamingplatform.api.dto.PlatformDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformResponse {
    private PlatformDTO platform;
}
