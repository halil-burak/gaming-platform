package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"NAME", "LANGUAGE_ID"}))
public class CategoryTranslation extends BaseEntity{

    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CATEGORY_TRANSLATION_ID")
    private List<CategorySlug> categorySlugs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID")
    private Language language;

    public void addCategorySlug(CategorySlug categorySlug) {
        this.getCategorySlugs().add(categorySlug);
    }

    public void removeCategorySlug(CategorySlug categorySlug) {
        this.getCategorySlugs().remove(categorySlug);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<CategorySlug> getCategorySlugs() {
        return categorySlugs;
    }

    @Override
    public String toString() {
        return "CategoryTranslation{" +
                "name='" + name + '\'' +
                ", categoryId=" + category.getId() +
                ", languageId=" + language.getId() +
                '}';
    }
}
