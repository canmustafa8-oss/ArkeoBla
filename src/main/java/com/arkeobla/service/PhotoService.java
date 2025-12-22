package com.arkeobla.service;

import com.arkeobla.model.Photo;
import com.arkeobla.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final Path uploadPath = Paths.get("uploads");

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Upload dizini oluşturulamadı!", e);
        }
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo uploadPhoto(String username, String description, MultipartFile file) throws IOException {
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath);

        Photo photo = new Photo("/uploads/" + filename, description, username, LocalDateTime.now());
        return photoRepository.save(photo);
    }

    public void likePhoto(Long photoId) {
        Optional<Photo> photoOpt = photoRepository.findById(photoId);
        if (photoOpt.isPresent()) {
            Photo photo = photoOpt.get();
            photo.setLikeCount(photo.getLikeCount() + 1);
            photoRepository.save(photo);
        }
    }

    public Optional<Photo> getPhotoOfTheMonth() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.withDayOfMonth(1).withHour(0).withMinute(0);
        LocalDateTime end = now.plusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0);

        return photoRepository.findTopByUploadedAtBetweenOrderByLikeCountDesc(start, end);
    }
}
