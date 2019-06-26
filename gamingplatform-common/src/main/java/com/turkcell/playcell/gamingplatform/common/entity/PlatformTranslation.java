package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlatformTranslation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "PLATFORM_ID")
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID")
    private Language language;

    /*@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PLATFORM_TRANSLATION_ID")*/
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "platformTranslation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SliderImage> sliderImages = new ArrayList<>();

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<SliderImage> getSliderImages() {
        return sliderImages;
    }

    public void setSliderImages(List<SliderImage> sliderImages) {
        this.sliderImages = sliderImages;
    }

    public void addSliderImage(SliderImage sliderImage) {
        this.getSliderImages().add(sliderImage);
    }

    public void removeSliderImage(SliderImage sliderImage) {
        this.getSliderImages().remove(sliderImage);
    }
}
