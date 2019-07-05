package com.turkcell.playcell.gamingplatform.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TariffDTO {
    private String name;
    private int grade;
    private String label;
    private String packageName;
    private Long offerId;
    private Long provisioningId;
}
