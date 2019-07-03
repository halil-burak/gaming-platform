package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Language extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2273491619735069936L;

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

    public Language(Long id) {
        super.setId(id);
    }
}
