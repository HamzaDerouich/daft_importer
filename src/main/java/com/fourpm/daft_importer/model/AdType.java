package com.fourpm.daft_importer.model;

public class AdType {

    private String name;

    private String desc;

    private String descPlural;

    private String descShort;

   public AdType() {
    }

    public AdType(String name, String desc, String descPlural, String descShort) {
        this.name = name;
        this.desc = desc;
        this.descPlural = descPlural;
        this.descShort = descShort;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescPlural() {
        return descPlural;
    }

    public void setDescPlural(String descPlural) {
        this.descPlural = descPlural;
    }

    public String getDescShort() {
        return descShort;
    }

    public void setDescShort(String descShort) {
        this.descShort = descShort;
    }
}
