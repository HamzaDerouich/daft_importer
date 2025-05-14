package com.fourpm.daft_importer.model;

public class PropertyType {
    private String name;
    private String plural;
    private String shortName;
    private String shortPlural;
    private String key;

    public PropertyType() {
    }

    // Constructor
    public PropertyType(String name, String plural, String shortName, String shortPlural, String key) {
        this.name = name;
        this.plural = plural;
        this.shortName = shortName;
        this.shortPlural = shortPlural;
        this.key = key;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortPlural() {
        return shortPlural;
    }

    public void setShortPlural(String shortPlural) {
        this.shortPlural = shortPlural;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}