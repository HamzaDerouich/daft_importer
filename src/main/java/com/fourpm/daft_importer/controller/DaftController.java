package com.fourpm.daft_importer.controller;

import com.fourpm.daft.wsclient.SaleAd;
import com.fourpm.daft_importer.service.DaftService;
import com.fourpm.daft_importer.service.PropertyListResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daft")
public class DaftController {

    private final DaftService daftService;

    public DaftController(DaftService daftService) {
        this.daftService = daftService;
    }

    @GetMapping("/properties")
    public ResponseEntity<PropertyListResult> getProperties(@RequestParam String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        PropertyListResult result = daftService.getPropertyList(apiKey);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/properties/{id}")
    public ResponseEntity<SaleAd> getPropertyById(
            @PathVariable Long id,
            @RequestParam String apiKey) {

        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return daftService.getPropertyById(id, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/properties/type")
    public ResponseEntity<List<SaleAd>> getPropertiesByType(
            @RequestParam String type,
            @RequestParam String apiKey) {

        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<SaleAd> properties = daftService.getPropertiesByType(type, apiKey);
        if (properties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/properties/price-range")
    public ResponseEntity<List<SaleAd>> getPropertiesByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice,
            @RequestParam String apiKey) {

        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<SaleAd> properties = daftService.getPropertiesByPriceRange(minPrice, maxPrice, apiKey);
        if (properties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(properties);
    }

}