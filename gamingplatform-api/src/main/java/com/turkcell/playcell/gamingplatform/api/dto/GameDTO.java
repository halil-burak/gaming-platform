package com.turkcell.playcell.gamingplatform.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GameDTO implements Serializable {
	private static final long serialVersionUID = 6947764558816729228L;
	private Long id;
    private String name;
    private boolean hideOnCategories;
    private int subscription;
    private int score;
    private String title;
    private String slug;
    private String description;
    private List<ImageDTO> images = new ArrayList<>();
    private String platform;
    private List<String> packages;
    private List<Long> categories;
}
