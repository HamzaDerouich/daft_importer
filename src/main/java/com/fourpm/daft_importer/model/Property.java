package com.fourpm.daft_importer.model;

import java.util.List;

public class Property {

    private Long id;
    private String description;
    private Double price;
    private String propertyType;
    private String houseType;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double size;
    private String address;
    private String city;
    private String county;
    private String country;
    private List<String> images;
    private List<String> features;
    private Boolean furnished;
    private Integer leaseLength;
    private String availableDate;

    public Property() {}

    public Property(Long id, String description, Double price, String propertyType, String houseType,
                    Integer bedrooms, Integer bathrooms, Double size, String address, String city,
                    String county, String country, List<String> images, List<String> features,
                    Boolean furnished, Integer leaseLength, String availableDate) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.propertyType = propertyType;
        this.houseType = houseType;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.size = size;
        this.address = address;
        this.city = city;
        this.county = county;
        this.country = country;
        this.images = images;
        this.features = features;
        this.furnished = furnished;
        this.leaseLength = leaseLength;
        this.availableDate = availableDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Integer getLeaseLength() {
        return leaseLength;
    }

    public void setLeaseLength(Integer leaseLength) {
        this.leaseLength = leaseLength;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }
}