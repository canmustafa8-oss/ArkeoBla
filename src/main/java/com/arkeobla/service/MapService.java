package com.arkeobla.service;

import com.arkeobla.model.MapLocation;
import com.arkeobla.repository.MapLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {

    private final MapLocationRepository mapLocationRepository;

    public MapService(MapLocationRepository mapLocationRepository) {
        this.mapLocationRepository = mapLocationRepository;
    }

    public List<MapLocation> getAllLocations() {
        return mapLocationRepository.findAll();
    }

    public MapLocation saveLocation(MapLocation location) {
        return mapLocationRepository.save(location);
    }
}
