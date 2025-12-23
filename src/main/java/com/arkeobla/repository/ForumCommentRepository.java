package com.arkeobla.repository;

import com.arkeobla.model.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {
}
