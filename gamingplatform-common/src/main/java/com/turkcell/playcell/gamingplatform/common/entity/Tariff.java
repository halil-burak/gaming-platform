package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tariff extends BaseEntity{

    @Column(name = "NAME")
    @NotNull @NotEmpty
    private String name;

    @NotNull
    @Column(name = "GRADE")
    private int grade;

    @Column(name = "LABEL")
    private String label = "";

    @Column(name = "PACKAGE_NAME")
    private String packageName = "";

    @Column(name = "OFFER_ID")
    private Long offerId = 0L;

    @Column(name = "PROVISIONING_ID")
    private Long provisioningId = 0L;

    @ManyToMany(mappedBy="tariffs")
    private List<GameDetail> gameDetails = new ArrayList<>();

    public Tariff() {
    }

    public Tariff(Long id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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

    public List<GameDetail> getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(List<GameDetail> gameDetails) {
        this.gameDetails = gameDetails;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", label='" + label + '\'' +
                ", packageName='" + packageName + '\'' +
                ", offerId=" + offerId +
                ", provisioningId=" + provisioningId +
                '}';
    }
}
