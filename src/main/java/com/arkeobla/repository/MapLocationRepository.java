package com.arkeobla.repository;

import com.arkeobla.model.MapLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapLocationRepository extends JpaRepository<MapLocation, Long> {
}
