package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints={@UniqueConstraint(name = "UK_CATEGORY_ICON", columnNames = {"PLATFORM_ID" , "IMAGE_ID", "CATEGORY_ID"})})
public class CategoryIcon extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2736976064465769061L;

	@ManyToOne
    @JoinColumn(name = "PLATFORM_ID")
    private Platform platform;

    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
