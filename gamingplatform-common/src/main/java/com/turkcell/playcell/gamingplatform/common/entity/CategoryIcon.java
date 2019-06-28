package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CategoryIcon extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "PLATFORM_ID")
    private Platform platform;

    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
