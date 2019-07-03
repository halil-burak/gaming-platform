package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class GameDetailTranslation extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7048719881381950875L;

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
}
