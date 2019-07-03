package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class PlatformTranslation extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2963330793195372531L;

	@ManyToOne
    @JoinColumn(name = "PLATFORM_ID")
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID")
    private Language language;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "platformTranslation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SliderImage> sliderImages = new ArrayList<>();

    public void addSliderImage(SliderImage sliderImage) {
        this.getSliderImages().add(sliderImage);
    }

    public void removeSliderImage(SliderImage sliderImage) {
        this.getSliderImages().remove(sliderImage);
    }
}
