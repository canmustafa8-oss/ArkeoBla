package com.arkeobla.controller;

import com.arkeobla.model.Photo;
import com.arkeobla.service.PhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public String gallery(Model model) {
        List<Photo> allPhotos = photoService.getAllPhotos();
        Optional<Photo> monthlyWinner = photoService.getPhotoOfTheMonth();

        model.addAttribute("photos", allPhotos);
        model.addAttribute("winner", monthlyWinner.orElse(null));
        return "photos";
    }

    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file,
            @RequestParam("description") String description,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {
        try {
            String username = (authentication != null) ? authentication.getName() : "Anonim";
            photoService.uploadPhoto(username, description, file);
            redirectAttributes.addFlashAttribute("message", "Fotoğraf başarıyla yüklendi! 📸");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Yükleme sırasında hata oluştu.");
        }
        return "redirect:/photos";
    }

    @PostMapping("/{id}/like")
    @ResponseBody
    public ResponseEntity<String> likePhoto(@PathVariable Long id) {
        photoService.likePhoto(id);
        return ResponseEntity.ok("Liked");
    }
}
