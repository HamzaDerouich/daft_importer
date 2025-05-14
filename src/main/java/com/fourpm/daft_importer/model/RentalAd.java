package com.fourpm.daft_importer.model;

import java.util.List;

public class RentalAd {
    private Long adId; // Cambiado a Long para ser consistente con Property
    private String description;
    private Double rent; // Cambiado a Double para ser consistente con Property
    private String city;
    private String propertyType;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double size;
    private String address;
    private String county;
    private String country;
    private List<String> images;
    private List<String> features;
    private Boolean furnished;
    private Integer leaseLength;
    private String availableDate;

    // Constructor vacío
    public RentalAd() {}

    // Constructor con todos los campos
    public RentalAd(Long adId, String description, Double rent, String city, String propertyType,
                    Integer bedrooms, Integer bathrooms, Double size, String address, String county,
                    String country, List<String> images, List<String> features, Boolean furnished,
                    Integer leaseLength, String availableDate) {
        this.adId = adId;
        this.description = description;
        this.rent = rent;
        this.city = city;
        this.propertyType = propertyType;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.size = size;
        this.address = address;
        this.county = county;
        this.country = country;
        this.images = images;
        this.features = features;
        this.furnished = furnished;
        this.leaseLength = leaseLength;
        this.availableDate = availableDate;
    }

    // Getters y Setters
    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
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