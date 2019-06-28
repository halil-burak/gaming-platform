package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity{

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

    public Category() {
    }

    public Category(Long id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSeries() {
        return isSeries;
    }

    public void setSeries(boolean series) {
        isSeries = series;
    }

    public boolean isTag() {
        return isTag;
    }

    public void setTag(boolean tag) {
        isTag = tag;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public List<CategoryTranslation> getCategoryTranslations() {
        return categoryTranslations;
    }

    public void setCategoryTranslations(List<CategoryTranslation> categoryTranslations) {
        this.categoryTranslations = categoryTranslations;
    }

    public List<GameDetail> getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(List<GameDetail> gameDetails) {
        this.gameDetails = gameDetails;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<CategoryIcon> getCategoryIcons() {
        return categoryIcons;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", isSeries=" + isSeries +
                ", isTag=" + isTag +
                ", isVisible=" + isVisible +
                '}';
    }
}
