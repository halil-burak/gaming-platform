package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Complaint extends BaseEntity {

    private String agent;

    private String complaint;

    @ManyToOne
    @JoinColumn(name = "GAME_DETAIL_ID")
    private GameDetail gameDetail;

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public GameDetail getGameDetail() {
        return gameDetail;
    }

    public void setGameDetail(GameDetail gameDetail) {
        this.gameDetail = gameDetail;
    }
}

