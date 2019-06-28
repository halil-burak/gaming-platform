package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Game extends BaseEntity{

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "HIDE_ON_CATEGORIES", nullable = false, columnDefinition = "number(1,0) default 0")
    private boolean hideOnCategories;

    @OneToOne
    @JoinColumn(name = "GAME_FILE_ID")
    private GameFile gameFile;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "GAME_ID")
    List<GameDetail> gameDetails = new ArrayList<>();

    @Column(name = "SCORE")
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private FtpAccount ftpAccount;

    public Game() {
    }
    public Game(Long id) {
        super.setId(id);
    }


    public void addGameDetail(GameDetail gameDetail) {
        this.gameDetails.add(gameDetail);
    }

    public void removeGameDetail(GameDetail gameDetail) {
        this.gameDetails.remove(gameDetail);
    }

    public List<GameDetail> getGameDetails() {
        return gameDetails;
    }

    public FtpAccount getFtpAccount() {
        return ftpAccount;
    }

    public void setFtpAccount(FtpAccount ftpAccount) {
        this.ftpAccount = ftpAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GameFile getGameFile() {
        return gameFile;
    }

    public void setGameFile(GameFile gameFile) {
        this.gameFile = gameFile;
    }

    public boolean isHideOnCategories() {
        return hideOnCategories;
    }

    public void setHideOnCategories(boolean hideOnCategories) {
        this.hideOnCategories = hideOnCategories;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", hideOnCategories=" + hideOnCategories +
                ", score=" + score +
                //", ftpAccountId=" + ftpAccount.getId() +
                '}';
    }
}
