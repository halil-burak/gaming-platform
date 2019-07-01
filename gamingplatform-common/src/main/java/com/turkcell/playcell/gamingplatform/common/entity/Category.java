package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Category extends BaseEntity implements Serializable {

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
    @Column(name = "IS_VISIBLE", nullable = false, columnDefinition = "number(1,0) default 0")
    private boolean isVisible;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CATEGORY_ID")
    private List<CategoryTranslation> categoryTranslations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CATEGORY_ID")
    private List<CategoryIcon> categoryIcons = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "GAME_DETAIL_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "GAME_DETAIL_ID"))
    private List<GameDetail> gameDetails = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "PLATFORM_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "PLATFORM_ID"))
    private List<Platform> platforms = new ArrayList<>();

    public Category(Long id) {
        super.setId(id);
    }
}
