package com.arkeobla.repository;

import com.arkeobla.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    // Belirli tarihler arasında en çok beğenilen tek fotoğrafı bul (Ayın Fotoğrafı)
    Optional<Photo> findTopByUploadedAtBetweenOrderByLikeCountDesc(LocalDateTime start, LocalDateTime end);

}
