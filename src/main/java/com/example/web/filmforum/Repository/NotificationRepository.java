package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Notification.NotificationPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationPO, Long> {
    Page<NotificationPO> findByToUser_IdOrderByCreateTimeDesc(Long toUserId, Pageable pageable);
    long countByToUser_IdAndReadFlagFalse(Long toUserId);
}

