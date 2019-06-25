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
public class Platform extends BaseEntity {

    @Column(name = "NAME", unique = true)
    @NotNull @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "platforms")
    @JsonIgnore
    List<Category> categories = new ArrayList<>();

    @ManyToMany(mappedBy = "platforms")
    @JsonIgnore
    List<Language> languages = new ArrayList<>();

    public Platform(Long id) {
        super.setId(id);
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Platform)) return false;
        return super.getId() != null && getId().equals(((Platform) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }*/
}
