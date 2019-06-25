package com.turkcell.playcell.gamingplatform.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"NAME", "LANGUAGE_ID"}))
public class CategoryTranslation extends BaseEntity{

    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_TRANSLATION_ID")
    @JsonIgnore
    private List<CategorySlug> categorySlugs;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID")
    @JsonIgnore
    private Language language;

}
