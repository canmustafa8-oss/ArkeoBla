package com.arkeobla.repository;

import com.arkeobla.model.AiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AiLogRepository extends JpaRepository<AiLog, Long> {
    List<AiLog> findTop50ByOrderByTimestampDesc();
}
