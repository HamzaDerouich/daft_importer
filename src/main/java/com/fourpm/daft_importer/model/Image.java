package com.fourpm.daft_importer.model;

public class Image {
    private String caption;
    private String largeUrl;
    private String mediumUrl;
    private String iphoneUrl;
    private String ipadSearchUrl;
    private String ipadGalleryUrl;
    private String smallUrl;

    public Image() {
    }

    public Image(String caption, String largeUrl, String mediumUrl, String iphoneUrl, String ipadSearchUrl, String ipadGalleryUrl, String smallUrl) {
        this.caption = caption;
        this.largeUrl = largeUrl;
        this.mediumUrl = mediumUrl;
        this.iphoneUrl = iphoneUrl;
        this.ipadSearchUrl = ipadSearchUrl;
        this.ipadGalleryUrl = ipadGalleryUrl;
        this.smallUrl = smallUrl;
    }

    // Getters y Setters
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public String getIphoneUrl() {
        return iphoneUrl;
    }

    public void setIphoneUrl(String iphoneUrl) {
        this.iphoneUrl = iphoneUrl;
    }

    public String getIpadSearchUrl() {
        return ipadSearchUrl;
    }

    public void setIpadSearchUrl(String ipadSearchUrl) {
        this.ipadSearchUrl = ipadSearchUrl;
    }

    public String getIpadGalleryUrl() {
        return ipadGalleryUrl;
    }

    public void setIpadGalleryUrl(String ipadGalleryUrl) {
        this.ipadGalleryUrl = ipadGalleryUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }
}
