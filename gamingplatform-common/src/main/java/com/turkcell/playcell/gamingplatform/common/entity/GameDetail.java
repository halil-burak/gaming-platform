package com.turkcell.playcell.gamingplatform.common.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"GAME_ID", "PLATFORM_ID"}))
public class GameDetail extends BaseEntity{

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    @JsonIgnore
    private Game game;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="gameDetail", orphanRemoval = true)
    private List<GameDetailTranslation> gameDetailTranslations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PLATFORM_ID")
    private Platform platform;

    @Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "number(1,0) default 0")
    private boolean isActive;

    @ManyToMany
    @JoinTable(name = "GAME_DETAIL_CATEGORY",
            joinColumns = @JoinColumn(name = "GAME_DETAIL_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<Category> categories = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "GAME_DETAIL_TARIFF",
            joinColumns = @JoinColumn(name = "GAME_DETAIL_ID"),
            inverseJoinColumns = @JoinColumn(name = "TARIFF_ID"))
    private List<Tariff> tariffs = new ArrayList<>();

    private Instant publishDatetime;

    private Instant unpublishDatetime;

    public void addGameDetailTranslations(GameDetailTranslation gameDetailTranslation) {
        this.gameDetailTranslations.add(gameDetailTranslation);
    }

    public void removeGameDetailTranslations(GameDetailTranslation gameDetailTranslation) {
        this.gameDetailTranslations.remove(gameDetailTranslation);
    }
    
}
