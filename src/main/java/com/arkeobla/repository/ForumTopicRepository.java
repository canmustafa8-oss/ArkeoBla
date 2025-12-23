package com.arkeobla.repository;

import com.arkeobla.model.ForumTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ForumTopicRepository extends JpaRepository<ForumTopic, Long> {
    List<ForumTopic> findAllByOrderByCreatedAtDesc();
}
