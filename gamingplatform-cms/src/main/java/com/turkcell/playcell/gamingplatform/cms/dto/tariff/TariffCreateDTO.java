package com.turkcell.playcell.gamingplatform.cms.dto.tariff;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TariffCreateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private int grade;

    @NotNull
    private String label;

    @NotNull @NotEmpty
    private String packageName;

    @NotNull
    private Long offerId;

    @NotNull
    private Long provisioningId;

}
