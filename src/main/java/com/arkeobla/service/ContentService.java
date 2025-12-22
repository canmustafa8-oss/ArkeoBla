package com.arkeobla.service;

import com.arkeobla.model.Content;
import com.arkeobla.model.User;
import com.arkeobla.repository.ContentRepository;
import com.arkeobla.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    public ContentService(ContentRepository contentRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }

    public Page<Content> getAllContents(Pageable pageable) {
        return contentRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<Content> getContentsByEra(String era, Pageable pageable) {
        return contentRepository.findByEra(era, pageable);
    }

    public Content getContentById(Long id) {
        return contentRepository.findById(id).orElseThrow(() -> new RuntimeException("İçerik bulunamadı"));
    }

    public void createContent(String title, String body, String category, String summary, String imageUrl,
            String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Content content = new Content();
        content.setTitle(title);
        content.setBody(body);
        content.setCategory(category);
        content.setSummary(summary);
        content.setImageUrl(imageUrl);
        content.setAuthor(author);

        contentRepository.save(content);
    }
}
