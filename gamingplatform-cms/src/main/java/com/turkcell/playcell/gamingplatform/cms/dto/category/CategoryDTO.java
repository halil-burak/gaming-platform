package com.turkcell.playcell.gamingplatform.cms.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CategoryDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private boolean isSeries;

    @NotNull
    private boolean isTag;

    @NotNull
    private boolean isVisible;

    @NotNull
    private List<Long> platforms;

    @NotNull
    private List<CategoryIconDTO> icons;

    @Valid
    @NotNull
    private List<CategoryTranslationCreateDTO> translations;

    @JsonProperty(value="isSeries")
    public boolean isSeries() {
        return isSeries;
    }

    @JsonProperty(value="isTag")
    public boolean isTag() {
        return isTag;
    }

    @JsonProperty(value="isVisible")
    public boolean isVisible() {
        return isVisible;
    }

}
