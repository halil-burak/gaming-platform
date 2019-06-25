package com.turkcell.playcell.gamingplatform.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class GameDetailTranslation extends BaseEntity{

    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "GAME_DETAIL_TRANSLATION_ID")
    private List<GameSlug> gameSlugs = new ArrayList<>();

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_DETAIL_ID")
    @JsonIgnore
    private GameDetail gameDetail;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID")
    private Language language;

    @ManyToMany
    @JoinTable(name = "GAME_DETAIL_TRANSLATION_IMAGE",
            joinColumns = @JoinColumn(name = "GAME_DETAIL_TRANSLATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "IMAGE_ID"))
    private List<Image> images = new ArrayList<>();

    public void addGameSlug(GameSlug gameSlug1) {
        this.getGameSlugs().add(gameSlug1);
    }

    public void removeGameSlug(GameSlug gameSlug1) {
        this.getGameSlugs().remove(gameSlug1);
    }
    
}
