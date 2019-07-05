package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Game extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1895591245944386546L;

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

    public Game(Long id) {
        super.setId(id);
    }

    public void addGameDetail(GameDetail gameDetail) {
        this.gameDetails.add(gameDetail);
    }

    public void removeGameDetail(GameDetail gameDetail) {
        this.gameDetails.remove(gameDetail);
    }
}
