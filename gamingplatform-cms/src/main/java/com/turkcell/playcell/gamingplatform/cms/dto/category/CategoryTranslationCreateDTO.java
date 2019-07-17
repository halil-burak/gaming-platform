package com.turkcell.playcell.gamingplatform.cms.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CategoryTranslationCreateDTO {

    private Long id;

    @NotNull
    private Long languageId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private List<String> slugs;

}
