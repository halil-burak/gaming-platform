package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.*;

@Entity
public class SliderImage extends BaseEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PLATFORM_TRANSLATION_ID")
    private PlatformTranslation platformTranslation;

    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sliderImage", orphanRemoval = true)
    @OneToOne//(mappedBy = "sliderImage", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    @Column(name = "EXTERNAL_URL")
    private String externalUrl;

    public PlatformTranslation getPlatformTranslation() {
        return platformTranslation;
    }

    public void setPlatformTranslation(PlatformTranslation platformTranslation) {
        this.platformTranslation = platformTranslation;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        /*if (image == null) {
            if (this.image != null) {
                this.image.setSliderImage(null);
            }
        } else {
            image.setSliderImage(this);
        }*/
        this.image = image;
    }
}
