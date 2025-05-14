package com.fourpm.daft_importer.model;

public class AgentInfo {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String fax;
    private boolean isBranded;
    private String mainLogoUrl;
    private String brandedLogoUrl;

    public AgentInfo() {
    }

    public AgentInfo(String id, String name, String address, String phone, String fax, boolean isBranded, String mainLogoUrl, String brandedLogoUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.isBranded = isBranded;
        this.mainLogoUrl = mainLogoUrl;
        this.brandedLogoUrl = brandedLogoUrl;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public boolean isBranded() {
        return isBranded;
    }

    public void setBranded(boolean isBranded) {
        this.isBranded = isBranded;
    }

    public String getMainLogoUrl() {
        return mainLogoUrl;
    }

    public void setMainLogoUrl(String mainLogoUrl) {
        this.mainLogoUrl = mainLogoUrl;
    }

    public String getBrandedLogoUrl() {
        return brandedLogoUrl;
    }

    public void setBrandedLogoUrl(String brandedLogoUrl) {
        this.brandedLogoUrl = brandedLogoUrl;
    }

    

    

}