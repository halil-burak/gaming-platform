package com.turkcell.playcell.gamingplatform.cms.dto.language;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LanguageCreateDTO {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String shortName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
