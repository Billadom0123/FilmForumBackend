package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Common.FavoritePO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoritePO, Long> {
    boolean existsByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
    FavoritePO findByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
    long countByTargetTypeAndTargetId(String targetType, Long targetId);
    // 新增：按目标删除全部收藏
    @Modifying
    @Transactional
    void deleteByTargetTypeAndTargetId(String targetType, Long targetId);
}
