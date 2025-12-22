package com.arkeobla.repository;

import com.arkeobla.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByOrderByCreatedAtDesc();

    // Pagination support
    Page<Content> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // Filter by Era with Pagination
    Page<Content> findByEra(String era, Pageable pageable);

    List<Content> findByCategory(String category);
}
