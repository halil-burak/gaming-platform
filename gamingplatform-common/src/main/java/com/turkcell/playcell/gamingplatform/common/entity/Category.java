package com.turkcell.playcell.gamingplatform.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Category extends BaseEntity {

	@NotNull @NotEmpty
    @Column(name = "NAME", unique = true)
    private String name;

    @NotNull
    @Column(name = "IS_SERIES")
    private boolean isSeries;

    @NotNull
    @Column(name = "IS_TAG")
    private boolean isTag;

    @NotNull
    @Column(name = "IS_VISIBLE")
    private boolean isVisible;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CATEGORY_ID")
    @JsonIgnore
    private List<CategoryTranslation> categoryTranslations = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "GAME_DETAIL_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "GAME_DETAIL_ID"))
    @JsonIgnore
    private List<GameDetail> gameDetails = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "PLATFORM_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "PLATFORM_ID"))
    @JsonIgnore
    private List<Platform> platforms = new ArrayList<>();

    public Category(Long id) {
        super.setId(id);
    }

}
