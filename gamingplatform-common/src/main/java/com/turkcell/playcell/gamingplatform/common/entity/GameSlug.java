package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"URL" , "PLATFORM_ID", "LANGUAGE_ID"})})
public class GameSlug extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(name = "URL")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_DETAIL_TRANSLATION_ID")
    private GameDetailTranslation gameDetailTranslation;

    // added for unique constraint
    @Column(name = "PLATFORM_ID")
    private Long platformId;
    @Column(name = "LANGUAGE_ID")
    private Long languageId;

    public GameSlug() { }

    public GameSlug(@NotNull @NotEmpty String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GameDetailTranslation getGameDetailTranslation() {
        return gameDetailTranslation;
    }

    public void setGameDetailTranslation(GameDetailTranslation gameDetailTranslation) {
        this.gameDetailTranslation = gameDetailTranslation;
        if(gameDetailTranslation == null) {
            this.platformId = null;
            this.languageId = null;
        }else {
            this.platformId = gameDetailTranslation.getGameDetail().getPlatform().getId();
            this.languageId = gameDetailTranslation.getLanguage().getId();
        }

    }

    @Override
    public String toString() {
        return "GameSlug{" +
                "url='" + url + '\'' +
                //", gameDetailTranslationId=" + gameDetailTranslation.getId() +
                '}';
    }
}
