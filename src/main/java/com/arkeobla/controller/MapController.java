package com.arkeobla.controller;

import com.arkeobla.model.MapLocation;
import com.arkeobla.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/map")
    public String showMap() {
        return "map";
    }

    @GetMapping("/api/locations")
    @ResponseBody
    public ResponseEntity<List<MapLocation>> getLocations() {
        return ResponseEntity.ok(mapService.getAllLocations());
    }
}
