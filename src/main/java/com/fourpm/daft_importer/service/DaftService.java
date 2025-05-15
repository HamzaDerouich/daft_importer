package com.fourpm.daft_importer.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fourpm.daft.wsclient.CommercialAd;
import com.fourpm.daft.wsclient.CommercialResults;
import com.fourpm.daft.wsclient.DaftAPIService;
import com.fourpm.daft.wsclient.DaftAPIService_Service;
import com.fourpm.daft.wsclient.NewDevelopmentAd;
import com.fourpm.daft.wsclient.NewDevelopmentResults;
import com.fourpm.daft.wsclient.Pagination;
import com.fourpm.daft.wsclient.RentalAd;
import com.fourpm.daft.wsclient.RentalResults;
import com.fourpm.daft.wsclient.SaleAd;
import com.fourpm.daft.wsclient.SaleResults;
import com.fourpm.daft.wsclient.SearchQuery;
import com.fourpm.daft.wsclient.ShorttermAd;
import com.fourpm.daft.wsclient.ShorttermResults;

/**
 * Service class for interacting with the Daft SOAP API.
 * Provides methods to fetch and filter property listings of various types.
 */
@Service
public class DaftService {
    private static final long RATE_LIMIT_DELAY = 250; // 1000/4 ms for 4 requests per second

    /**
     * Fetches all property types from the Daft API and returns them in a PropertyListResult.
     * @param apiKey The API key for authentication.
     * @return PropertyListResult containing lists of all property types.
     */
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

    /**
     * Handles rate limiting by pausing execution for a fixed delay.
     */
    private void handleRateLimit() {
        try {
            Thread.sleep(RATE_LIMIT_DELAY);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Rate limiting interrupted", e);
        }
    }

    /**
     * Fetches all pages of results from the Daft API for a given property type.
     * @param apiKey The API key for authentication.
     * @param fetcher A function that fetches a page of results.
     * @param <T> The type of property ad.
     * @return List of all property ads of type T.
     */
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

    /**
     * Creates a base SearchQuery with default parameters.
     * @return SearchQuery with default settings.
     */
    private SearchQuery createBaseSearchQuery() {
        SearchQuery query = new SearchQuery();
        query.setAgreed(BigInteger.valueOf(2));
        query.setSortBy("date");
        return query;
    }

    /**
     * Adapts SaleResults to a PaginatedResult of SaleAd.
     */
    private PaginatedResult<SaleAd> adaptSaleResults(SaleResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    /**
     * Adapts CommercialResults to a PaginatedResult of CommercialAd.
     */
    private PaginatedResult<CommercialAd> adaptCommercialResults(CommercialResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    /**
     * Adapts NewDevelopmentResults to a PaginatedResult of NewDevelopmentAd.
     */
    private PaginatedResult<NewDevelopmentAd> adaptNewDevelopmentResults(NewDevelopmentResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    /**
     * Adapts RentalResults to a PaginatedResult of RentalAd.
     */
    private PaginatedResult<RentalAd> adaptRentalResults(RentalResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    /**
     * Adapts ShorttermResults to a PaginatedResult of ShorttermAd.
     */
    private PaginatedResult<ShorttermAd> adaptShorttermResults(ShorttermResults results) {
        return new PaginatedResultAdapter<>(results.getAds(), results.getPagination());
    }

    /**
     * Adapter class to wrap ads and pagination into a PaginatedResult.
     */
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

    /**
     * Retrieves a SaleAd property by its ID.
     * @param id The property ID.
     * @param apiKey The API key for authentication.
     * @return Optional containing the SaleAd if found, or empty if not.
     */
    public Optional<SaleAd> getPropertyById(Long id, String apiKey) { 
        DaftAPIService_Service service = new DaftAPIService_Service();
        DaftAPIService api = service.getDaftAPIService();
        List<SaleAd> saleAds = fetchAllPages(apiKey, (key, query) -> adaptSaleResults(api.searchSale(key, query)));
    
        return saleAds.stream()
                .filter(saleAd -> saleAd.getAdId().longValue() == id)
                .findFirst();
    }

    /**
     * Retrieves a list of SaleAd properties by property type.
     * @param type The property type (e.g., "house", "apartment").
     * @param apiKey The API key for authentication.
     * @return List of SaleAd matching the given type.
     */
    public List<SaleAd> getPropertiesByType(String type, String apiKey) {
        DaftAPIService_Service service = new DaftAPIService_Service();
        DaftAPIService api = service.getDaftAPIService();
        List<SaleAd> saleAds = fetchAllPages(apiKey, (key, query) -> adaptSaleResults(api.searchSale(key, query)));
    
        return saleAds.stream()
                .filter(saleAd -> saleAd.getPropertyType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
   
    /**
     * Retrieves a list of SaleAd properties within a price range.
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @param apiKey The API key for authentication.
     * @return List of SaleAd within the specified price range.
     * 
     */
    public List<SaleAd> getPropertiesByPriceRange(Double minPrice, Double maxPrice, String apiKey) {
        DaftAPIService_Service service = new DaftAPIService_Service();
        DaftAPIService api = service.getDaftAPIService();
        List<SaleAd> saleAds = fetchAllPages(apiKey, (key, query) -> adaptSaleResults(api.searchSale(key, query)));
    
        return saleAds.stream()
                .filter(saleAd -> saleAd.getPrice() != null && saleAd.getPrice().doubleValue() >= minPrice && saleAd.getPrice().doubleValue() <= maxPrice)
                .collect(Collectors.toList());
    }

    
}