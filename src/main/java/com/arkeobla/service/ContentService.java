package com.arkeobla.service;

import com.arkeobla.model.Content;
import com.arkeobla.model.User;
import com.arkeobla.repository.ContentRepository;
import com.arkeobla.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    public ContentService(ContentRepository contentRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }

    public List<Content> getAllContents() {
        return contentRepository.findAllByOrderByCreatedAtDesc();
    }

    public void createContent(String title, String body, String category, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        
        Content content = new Content();
        content.setTitle(title);
        content.setBody(body);
        content.setCategory(category);
        content.setAuthor(author);
        
        contentRepository.save(content);
    }
}
