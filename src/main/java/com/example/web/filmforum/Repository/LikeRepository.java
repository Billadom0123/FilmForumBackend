package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Common.LikePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikePO, Long> {
    boolean existsByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
    LikePO findByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
    long countByTargetTypeAndTargetId(String targetType, Long targetId);
}

