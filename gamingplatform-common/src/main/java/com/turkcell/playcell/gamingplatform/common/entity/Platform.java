package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Platform extends BaseEntity {

    @Column(name = "NAME", unique = true)
    @NotNull @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "platforms")
    List<Category> categories = new ArrayList<>();

    @ManyToMany(mappedBy = "platforms")
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

    public Platform() { }

    public Platform(Long id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public FtpAccount getFtpAccount() {
        return ftpAccount;
    }

    public void setFtpAccount(FtpAccount ftpAccount) {
        this.ftpAccount = ftpAccount;
    }

    public List<PlatformTranslation> getPlatformTranslations() {
        return platformTranslations;
    }

    public void addPlatformTranslation(PlatformTranslation platformTranslation) {
        this.getPlatformTranslations().add(platformTranslation);
    }

    public void removePlatformTranslation(PlatformTranslation platformTranslation) {
        this.getPlatformTranslations().remove(platformTranslation);
    }

    public Image getLogo() {
        return logo;
    }

    public List<CategoryIcon> getCategoryIcons() {
        return categoryIcons;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
}
