package com.turkcell.playcell.gamingplatform.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"URL", "GAME_DETAIL_TRANSLATION_ID"}))
public class GameSlug extends BaseEntity {
    
	@Column(name = "URL")
    @NotNull
    @NotEmpty
    private String url;

    @ManyToOne
    @JoinColumn(name = "GAME_DETAIL_TRANSLATION_ID")
    @JsonIgnore
    private GameDetailTranslation gameDetailTranslation;

}
