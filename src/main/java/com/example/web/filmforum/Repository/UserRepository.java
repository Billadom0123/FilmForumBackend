package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.User.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {
    UserPO findByUsername(String username);
    Boolean existsByUsername(String username);
    UserPO findByEmail(String email);
    Boolean existsByEmail(String email);
    UserPO findByOpenId(String openId);
    Boolean existsByOpenId(String openId);
}
