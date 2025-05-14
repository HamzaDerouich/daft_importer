package com.fourpm.daft_importer.model;

public class Feature {
    private String id;
    private String name;

    public Feature() {
    }

    public Feature(String id, String name) {
        this.id = id;
        this.name = name;
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
}
