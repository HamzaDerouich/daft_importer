package com.fourpm.daft_importer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fourpm.daft_importer.service.DaftService;
import com.fourpm.daft_importer.service.PropertyListResult;
import com.fourpm.daft_importer.service.SaleAdWithMedia;

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
    public ResponseEntity<SaleAdWithMedia> getPropertyById(
            @PathVariable Long id,
            @RequestParam String apiKey) {

        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return daftService.getPropertyByIdWithMedia(id, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/properties/type")
    public ResponseEntity<List<SaleAdWithMedia>> getPropertiesByType(
            @RequestParam String type,
            @RequestParam String apiKey) {

        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<SaleAdWithMedia> properties = daftService.getPropertiesByTypeWithMedia(type, apiKey);
        if (properties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/properties/price-range")
    public ResponseEntity<List<SaleAdWithMedia>> getPropertiesByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice,
            @RequestParam String apiKey) {

        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<SaleAdWithMedia> properties = daftService.getPropertiesByPriceRangeWithMedia(minPrice, maxPrice, apiKey);
        if (properties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(properties);
    }
}