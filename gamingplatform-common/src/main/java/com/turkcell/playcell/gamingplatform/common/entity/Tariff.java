package com.turkcell.playcell.gamingplatform.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Tariff extends BaseEntity{

    @Column(name = "NAME", unique = true)
    @NotNull @NotEmpty
    private String name;

    @Column(name = "GRADE")
    private int grade;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "PACKAGE_NAME")
    private String packageName;

    @Column(name = "OFFER_ID")
    private Long offerId;

    @Column(name = "PROVISIONING_ID")
    private Long provisioningId;

    @ManyToMany(mappedBy="tariffs")
    @JsonIgnore
    private List<GameDetail> gameDetails = new ArrayList<>();

    public Tariff(Long id) {
        super.setId(id);
    }

}
