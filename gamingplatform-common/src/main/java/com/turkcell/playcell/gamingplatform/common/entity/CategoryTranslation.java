package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"NAME", "LANGUAGE_ID"}))
public class CategoryTranslation extends BaseEntity implements Serializable {

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
}
