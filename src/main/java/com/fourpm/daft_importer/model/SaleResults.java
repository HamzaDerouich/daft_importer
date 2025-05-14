package com.fourpm.daft_importer.model;

import java.util.List;

public class SaleResults {
    private String searchSentence;
    private Pagination pagination;
    private List<SaleAd> ads;

    public SaleResults() {
    }

    public SaleResults(String searchSentence, Pagination pagination, List<SaleAd> ads) {
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

    public List<SaleAd> getAds() {
        return ads;
    }

    public void setAds(List<SaleAd> ads) {
        this.ads = ads;
    }
}