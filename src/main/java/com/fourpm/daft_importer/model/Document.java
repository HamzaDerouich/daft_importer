package com.fourpm.daft_importer.model;

public class Document {
    private String caption;
    private String url;

    public Document()
    {
        
    }

    public Document(String caption, String url) {
        this.caption = caption;
        this.url = url;
    }

    // Getters y Setters
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
