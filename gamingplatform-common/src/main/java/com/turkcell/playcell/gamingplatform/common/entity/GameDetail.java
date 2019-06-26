package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"GAME_ID", "PLATFORM_ID"}))
public class GameDetail extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="gameDetail", orphanRemoval = true)
    private List<GameDetailTranslation> gameDetailTranslations = new ArrayList();

    @ManyToOne
    @JoinColumn(name = "PLATFORM_ID")
    private Platform platform;

    // Oracle için ozellestirdik.
    @Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "number(1,0) default 0")
    private boolean isActive;

    @ManyToMany
    @JoinTable(name = "GAME_DETAIL_CATEGORY",
            joinColumns = @JoinColumn(name = "GAME_DETAIL_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<Category> categories = new ArrayList();

    @ManyToMany
    @JoinTable(name = "GAME_DETAIL_TARIFF",
            joinColumns = @JoinColumn(name = "GAME_DETAIL_ID"),
            inverseJoinColumns = @JoinColumn(name = "TARIFF_ID"))
    private List<Tariff> tariffs = new ArrayList();

    private Instant publishDatetime;

    private Instant unpublishDatetime;

    public void addGameDetailTranslations(GameDetailTranslation gameDetailTranslation) {
        this.gameDetailTranslations.add(gameDetailTranslation);
    }

    public void removeGameDetailTranslations(GameDetailTranslation gameDetailTranslation) {
        this.gameDetailTranslations.remove(gameDetailTranslation);
    }

    public List<GameDetailTranslation> getGameDetailTranslations() {
        return gameDetailTranslations;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Instant getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(Instant publishDatetime) {
        this.publishDatetime = publishDatetime;
    }

    public Instant getUnpublishDatetime() {
        return unpublishDatetime;
    }

    public void setUnpublishDatetime(Instant unpublishDatetime) {
        this.unpublishDatetime = unpublishDatetime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "GameDetail{" +
               // "gameId=" + game.getId() +
                ", platformId=" + platform.getId() +
                ", isActive=" + isActive +
                //", categories=" + categories +
                //", tariffs=" + tariffs +
                ", publishDatetime=" + publishDatetime +
                ", unpublishDatetime=" + unpublishDatetime +
                '}';
    }
}
