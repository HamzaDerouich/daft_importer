package com.fourpm.daft_importer.model;

public class Area {
    private String id;
    private String name;
    private String properties;

    public Area() {
    }

    public Area(String id, String name, String properties) {
        this.id = id;
        this.name = name;
        this.properties = properties;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}