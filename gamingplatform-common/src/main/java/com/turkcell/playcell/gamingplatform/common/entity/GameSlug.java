package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={@UniqueConstraint(name = "UK_GAME_SLUG", columnNames = {"URL" , "PLATFORM_ID", "LANGUAGE_ID"})})
public class GameSlug extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6211105307405206495L;

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

    public GameSlug(@NotNull @NotEmpty String url) {
        this.url = url;
    }

    public void setGameDetailTranslation(GameDetailTranslation gameDetailTranslation) {
        this.gameDetailTranslation = gameDetailTranslation;
        if(gameDetailTranslation == null) {
            this.platformId = null;
            this.languageId = null;
        } else {
            this.platformId = gameDetailTranslation.getGameDetail().getPlatform().getId();
            this.languageId = gameDetailTranslation.getLanguage().getId();
        }
    }
}
