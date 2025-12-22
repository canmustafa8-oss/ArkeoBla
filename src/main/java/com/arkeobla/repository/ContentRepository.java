package com.arkeobla.repository;

import com.arkeobla.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByOrderByCreatedAtDesc(); // En yeniden eskiye sırala
    List<Content> findByCategory(String category);
}
