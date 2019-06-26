package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class CategorySlug extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(name = "URL", unique = true)
    private String url;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_TRANSLATION_ID")
    private CategoryTranslation categoryTranslation;

    public CategorySlug() { }

    public CategorySlug(Long id) {
        super.setId(id);
    }

    public CategorySlug(@NotNull @NotEmpty String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CategoryTranslation getCategoryTranslation() {
        return categoryTranslation;
    }

    public void setCategoryTranslation(CategoryTranslation categoryTranslation) {
        this.categoryTranslation = categoryTranslation;
    }

    @Override
    public String toString() {
        return "CategorySlug{" +
                "url='" + url + '\'' +
                ", categoryTranslationId=" + categoryTranslation.getId() +
                '}';
    }
}
