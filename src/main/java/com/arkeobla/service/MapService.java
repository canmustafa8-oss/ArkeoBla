package com.arkeobla.service;

import com.arkeobla.model.MapLocation;
import com.arkeobla.repository.MapLocationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {

    private final MapLocationRepository mapLocationRepository;
    private List<MapLocation> cachedLocations = new ArrayList<>();

    public MapService(MapLocationRepository mapLocationRepository) {
        this.mapLocationRepository = mapLocationRepository;
    }

    @jakarta.annotation.PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<MapLocation> loadedLocations = mapper.readValue(
                    new ClassPathResource("locations.json").getInputStream(),
                    new TypeReference<List<MapLocation>>() {
                    });

            // Veritabanı boşsa doldur, doluysa veritabanını kullan veya JSON'u
            // önceliklendir.
            // Performans için şimdilik JSON'u bellekte tutalım.
            this.cachedLocations = loadedLocations;

            // İsteğe bağlı: DB'ye kaydet
            if (mapLocationRepository.count() == 0) {
                mapLocationRepository.saveAll(loadedLocations);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lokasyon verileri yüklenemedi: " + e.getMessage());
        }
    }

    public List<MapLocation> getAllLocations() {
        // Eğer JSON yüklendiyse onu döndür, yoksa DB'den bak
        if (!cachedLocations.isEmpty()) {
            return cachedLocations;
        }
        return mapLocationRepository.findAll();
    }

    public MapLocation saveLocation(MapLocation location) {
        return mapLocationRepository.save(location);
    }
}
