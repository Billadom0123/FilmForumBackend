package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Common.FavoritePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoritePO, Long> {
    boolean existsByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
    FavoritePO findByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
    long countByTargetTypeAndTargetId(String targetType, Long targetId);
}

