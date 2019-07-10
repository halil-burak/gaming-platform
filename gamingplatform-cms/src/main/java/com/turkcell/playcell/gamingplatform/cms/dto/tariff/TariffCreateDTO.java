package com.turkcell.playcell.gamingplatform.cms.dto.tariff;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getProvisioningId() {
        return provisioningId;
    }

    public void setProvisioningId(Long provisioningId) {
        this.provisioningId = provisioningId;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
