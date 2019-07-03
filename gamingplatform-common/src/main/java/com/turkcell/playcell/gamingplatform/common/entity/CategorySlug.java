package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
public class CategorySlug extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3242995852046486614L;

	@NotNull
    @NotEmpty
    @Column(name = "URL", unique = true)
    private String url;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_TRANSLATION_ID")
    private CategoryTranslation categoryTranslation;
}
