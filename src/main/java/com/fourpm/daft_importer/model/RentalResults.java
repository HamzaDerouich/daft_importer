package com.fourpm.daft_importer.model;

import java.util.List;

public class RentalResults {
    private String searchSentence;
    private Pagination pagination;
    private List<RentalAd> ads;

    public RentalResults() {
    }

    public RentalResults(String searchSentence, Pagination pagination, List<RentalAd> ads) {
        this.searchSentence = searchSentence;
        this.pagination = pagination;
        this.ads = ads;
    }

    // Getters y Setters
    public String getSearchSentence() {
        return searchSentence;
    }

    public void setSearchSentence(String searchSentence) {
        this.searchSentence = searchSentence;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<RentalAd> getAds() {
        return ads;
    }

    public void setAds(List<RentalAd> ads) {
        this.ads = ads;
    }
}