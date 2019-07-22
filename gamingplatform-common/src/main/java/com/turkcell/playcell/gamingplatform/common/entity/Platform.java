package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Platform extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5935406666950883248L;

	@Column(name = "NAME", unique = true)
    @NotNull @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "platforms")
    List<Category> categories = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "PLATFORM_LANGUAGE",
            joinColumns = @JoinColumn(name = "PLATFORM_ID"),
            inverseJoinColumns = @JoinColumn(name = "LANGUAGE_ID"))
    List<Language> languages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private FtpAccount ftpAccount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="platform", orphanRemoval = true)
    private List<PlatformTranslation> platformTranslations = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private Image logo;

    @OneToMany(fetch= FetchType.LAZY)
    @JoinColumn(name = "PLATFORM_ID")
    private List<CategoryIcon> categoryIcons = new ArrayList<>();

    public Platform(Long id) {
        super.setId(id);
    }

    public void addPlatformTranslation(PlatformTranslation platformTranslation) {
        this.getPlatformTranslations().add(platformTranslation);
    }

    public void removePlatformTranslation(PlatformTranslation platformTranslation) {
        this.getPlatformTranslations().remove(platformTranslation);
    }
}
