package com.fourpm.daft_importer.service;

import org.springframework.stereotype.Service;
import com.fourpm.daft.wsclient.*;
import com.fourpm.daft_importer.model.Property;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public class DaftService {
    private static final long RATE_LIMIT_DELAY = 250; // 1000/4 ms for 4 requests per second

    public PropertyListResult getPropertyList(String apiKey) {
        DaftAPIService_Service service = new DaftAPIService_Service();
        DaftAPIService api = service.getDaftAPIService();

        PropertyListResult result = new PropertyListResult();
        result.setSales(fetchAllPages(apiKey, (key, query) -> adaptSaleResults(api.searchSale(key, query))));
        result.setCommercial(fetchAllPages(apiKey, (key, query) -> adaptCommercialResults(api.searchCommercial(key, query))));
        result.setNewDevelopment(fetchAllPages(apiKey, (key, query) -> adaptNewDevelopmentResults(api.searchNewDevelopment(key, query))));
        result.setRental(fetchAllPages(apiKey, (key, query) -> adaptRentalResults(api.searchRental(key, query))));
        result.setShortterm(fetchAllPages(apiKey, (key, query) -> adaptShorttermResults(api.searchShortterm(key, query))));

        return result;
    }

    private void handleRateLimit() {
        try {
            Thread.sleep(RATE_LIMIT_DELAY);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Rate limiting interrupted", e);
        }
    }

    private <T> List<T> fetchAllPages(String apiKey, BiFunction<String, SearchQuery, ? extends PaginatedResult<T>> fetcher) {
        List<T> results = new ArrayList<>();
        SearchQuery query = createBaseSearchQuery();
        int currentPage = 0;
        boolean completed = false;

        try {
            do {
                query.setPage(BigInteger.valueOf(++currentPage));
                PaginatedResult<T> response = fetcher.apply(apiKey, query);
                results.addAll(response.getAds());

                Pagination pagination = response.getPagination();
                completed = pagination.getNumPages().intValue() == 0 ||
                        pagination.getCurrentPage().intValue() >= pagination.getNumPages().intValue();
                handleRateLimit();
            } while (!completed);
            return results;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching data from Daft API: " + e.getMessage(), e);
        }
    }

    private SearchQuery createBaseSearchQuery() {
        SearchQuery query = new SearchQuery();
        query.setAgreed(BigInteger.valueOf(2));
        query.setSortBy("date");
        return query;
    }

    private PaginatedResult<SaleAd> adaptSaleResults(SaleResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    private PaginatedResult<CommercialAd> adaptCommercialResults(CommercialResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    private PaginatedResult<NewDevelopmentAd> adaptNewDevelopmentResults(NewDevelopmentResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    private PaginatedResult<RentalAd> adaptRentalResults(RentalResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    private PaginatedResult<ShorttermAd> adaptShorttermResults(ShorttermResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    private static class PaginatedResultAdapter<T> implements PaginatedResult<T> {
        private final List<T> ads;
        private final Pagination pagination;

        public PaginatedResultAdapter(List<T> ads, Pagination pagination) {
            this.ads = ads;
            this.pagination = pagination;
        }

        @Override
        public List<T> getAds() {
            return ads;
        }

        @Override
        public Pagination getPagination() {
            return pagination;
        }
    }

    public Optional<SaleAd> getPropertyById(Long id, String apiKey) { 
        DaftAPIService_Service service = new DaftAPIService_Service();
        DaftAPIService api = service.getDaftAPIService();
        List<SaleAd> saleAds = fetchAllPages(apiKey, (key, query) -> adaptSaleResults(api.searchSale(key, query)));
    
        return saleAds.stream()
                .filter(saleAd -> saleAd.getAdId().longValue() == id)
                .findFirst();
    }

    public List<SaleAd> getPropertiesByType(String type, String apiKey) {
        DaftAPIService_Service service = new DaftAPIService_Service();
        DaftAPIService api = service.getDaftAPIService();
        List<SaleAd> saleAds = fetchAllPages(apiKey, (key, query) -> adaptSaleResults(api.searchSale(key, query)));
    
        return saleAds.stream()
                .filter(saleAd -> saleAd.getPropertyType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
   
    public List<SaleAd> getPropertiesByPriceRange(Double minPrice, Double maxPrice, String apiKey) {
        DaftAPIService_Service service = new DaftAPIService_Service();
        DaftAPIService api = service.getDaftAPIService();
        List<SaleAd> saleAds = fetchAllPages(apiKey, (key, query) -> adaptSaleResults(api.searchSale(key, query)));
    
        return saleAds.stream()
                .filter(saleAd -> saleAd.getPrice() != null && saleAd.getPrice().doubleValue() >= minPrice && saleAd.getPrice().doubleValue() <= maxPrice)
                .collect(Collectors.toList());
    }
}