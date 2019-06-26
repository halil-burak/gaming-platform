package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameDetailTranslation extends BaseEntity{

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "GAME_DETAIL_TRANSLATION_ID")
    private List<GameSlug> gameSlugs = new ArrayList<>();

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_DETAIL_ID")
    private GameDetail gameDetail;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID")
    private Language language;

    @ManyToMany
    @JoinTable(name = "GAME_DETAIL_TRANSLATION_IMAGE",
            joinColumns = @JoinColumn(name = "GAME_DETAIL_TRANSLATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "IMAGE_ID"))
    private List<Image> images = new ArrayList<>();

    public void addGameSlug(GameSlug gameSlug) {
        gameSlug.setGameDetailTranslation(this);
        this.getGameSlugs().add(gameSlug);
    }

    public void removeGameSlug(GameSlug gameSlug) {
        gameSlug.setGameDetailTranslation(null);
        this.getGameSlugs().remove(gameSlug);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public GameDetail getGameDetail() {
        return gameDetail;
    }

    public void setGameDetail(GameDetail gameDetail) {
        this.gameDetail = gameDetail;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<GameSlug> getGameSlugs() {
        return gameSlugs;
    }

    @Override
    public String toString() {
        return "GameDetailTranslation{" +
                "name='" + name + '\'' +
                //", gameSlugs=" + gameSlugs +
                ", description='" + description + '\'' +
                //", gameDetailId=" + gameDetail.getId() +
                ", languageId=" + language.getId() +
                '}';
    }
}
