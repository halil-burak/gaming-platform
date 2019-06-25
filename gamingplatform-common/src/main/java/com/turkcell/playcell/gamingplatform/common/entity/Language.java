package com.turkcell.playcell.gamingplatform.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Language extends BaseEntity{

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "SHORT_NAME", unique = true)
    private String shortName;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "PLATFORM_LANGUAGE",
            joinColumns = { @JoinColumn(name = "LANGUAGE_ID", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "PLATFORM_ID", referencedColumnName = "ID") })
    @JsonIgnore
    private List<Platform> platforms = new ArrayList<>();
    
}
