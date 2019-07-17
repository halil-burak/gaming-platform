package com.turkcell.playcell.gamingplatform.cms.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryGetDTO {

    private Long id;

    private String name;

    private boolean isSeries;

    private boolean isTag;

    private boolean isVisible;

    private List<Long> platforms;

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
