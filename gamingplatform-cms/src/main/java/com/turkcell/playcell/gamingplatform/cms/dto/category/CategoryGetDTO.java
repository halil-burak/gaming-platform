package com.turkcell.playcell.gamingplatform.cms.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CategoryGetDTO {

    private Long id;

    private String name;

    private boolean isSeries;

    private boolean isTag;

    private boolean isVisible;

    private List<Long> platforms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(value="isSeries")
    public boolean isSeries() {
        return isSeries;
    }

    @JsonProperty(value="isTag")
    public boolean isTag() {
        return isTag;
    }

    public void setTag(boolean tag) {
        isTag = tag;
    }

    @JsonProperty(value="isVisible")
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setSeries(boolean isSeries) {
        this.isSeries = isSeries;
    }

    public List<Long> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Long> platforms) {
        this.platforms = platforms;
    }
}
