package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Game extends BaseEntity{

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "SCORE")
    private int score;

    @Column(name = "IS_BLOCK_LINK")
    private boolean isBlockLink;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "GAME_ID")
    List<GameDetail> gameDetails = new ArrayList<>();

    public void addGameDetail(GameDetail gameDetail) {
        this.gameDetails.add(gameDetail);
    }

    public void removeGameDetail(GameDetail gameDetail) {
        this.gameDetails.remove(gameDetail);
    }

}
