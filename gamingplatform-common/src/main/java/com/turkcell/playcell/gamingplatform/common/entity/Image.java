package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Image extends BaseEntity {

    private Long sizeId;

    private String cdnUrl;

    private String path;

    @ManyToMany(mappedBy = "images")
    private List<GameDetailTranslation>  gameDetailTranslations = new ArrayList<>();
}
