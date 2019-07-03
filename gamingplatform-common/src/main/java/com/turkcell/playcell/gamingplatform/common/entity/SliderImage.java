package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
public class SliderImage extends BaseEntity implements Serializable {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PLATFORM_TRANSLATION_ID")
    private PlatformTranslation platformTranslation;

    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    @Column(name = "EXTERNAL_URL")
    private String externalUrl;
}
