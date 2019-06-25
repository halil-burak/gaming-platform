package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class CategorySlug extends BaseEntity {
    
	@Column(name = "URL", unique = true)
    @NotNull
    @NotEmpty
    private String url;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_TRANSLATION_ID")
    private CategoryTranslation categoryTranslation;

    public CategorySlug(Long id) {
        super.setId(id);
    }

}
