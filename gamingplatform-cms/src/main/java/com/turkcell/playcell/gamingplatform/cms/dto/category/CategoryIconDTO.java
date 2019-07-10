package com.turkcell.playcell.gamingplatform.cms.dto.category;

public class CategoryIconDTO {

    private Long id;

    private Long platformId;

    private String url;

    private Long imageId;

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImageId() { return imageId; }

    public void setImageId(Long imageId) { this.imageId = imageId; }
}
