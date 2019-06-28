package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Language extends BaseEntity{

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "SHORT_NAME", unique = true)
    private String shortName;

    @ManyToMany
    @JoinTable(name = "PLATFORM_LANGUAGE",
            joinColumns = { @JoinColumn(name = "LANGUAGE_ID", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "PLATFORM_ID", referencedColumnName = "ID") })
    private List<Platform> platforms = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
//    @JoinColumn(name = "PLATFORM_ID")
//    private List<GameSlug> gameSlugs = new ArrayList<>();

    public Language() {
    }

    public Language(Long id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

//    public List<GameSlug> getGameSlugs() {
//        return gameSlugs;
//    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                //", platforms=" + platforms +
                '}';
    }
}
