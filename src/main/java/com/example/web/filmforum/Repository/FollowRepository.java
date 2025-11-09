package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.User.FollowPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowPO, Long> {
    boolean existsByFollower_IdAndFollowing_Id(Long followerId, Long followingId);
    FollowPO findByFollower_IdAndFollowing_Id(Long followerId, Long followingId);

    // 新增: 统计粉丝数量
    long countByFollowing_Id(Long followingId);
    // 新增: 统计关注数量
    long countByFollower_Id(Long followerId);
    // 新增: 分页查询该用户关注的用户
    Page<FollowPO> findByFollower_Id(Long followerId, Pageable pageable);
}
