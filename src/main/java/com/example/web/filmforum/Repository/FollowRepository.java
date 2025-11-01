package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.User.FollowPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowPO, Long> {
    boolean existsByFollower_IdAndFollowing_Id(Long followerId, Long followingId);
    FollowPO findByFollower_IdAndFollowing_Id(Long followerId, Long followingId);
}

