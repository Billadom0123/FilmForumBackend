package com.example.web.filmforum.Service.Login;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Payload.Enums.UserType;
import com.example.web.filmforum.Repository.UserRepository;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public DataResponse registerUser(JSONObject payload) {
        String username = payload.getString("username");
        String nickname = payload.getString("nickname");
        String email = payload.getString("email");
        String password = payload.getString("password");

        if (userRepository.existsByUsername(username)) {
            return DataResponse.failure(CommonErr.USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(email)) {
            return DataResponse.failure(CommonErr.EMAIL_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(password);
        UserPO newUser = new UserPO();
        newUser.setUsername(username);
        newUser.setNickname(nickname);
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setRole(UserType.ROLE_USER); // 默认角色为USER
        newUser.setLevel(1);
        newUser.setExp(0);
        newUser.setJoinDate(LocalDateTime.now());
        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            return DataResponse.failure(CommonErr.UNKNOWN_REGISTER_ERROR);
        }


        return DataResponse.success(H.build()
                .put("username", username)
                .put("nickname", nickname)
                .put("email", email)
                .put("role", newUser.getRole().getString())
                .put("level", newUser.getLevel())
                .put("exp", H.build()
                        .put("now", newUser.getExp())
                        .put("next", newUser.getLevel() * 100)
                        .toJson())
                .put("joinDate", newUser.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .put("avatar", newUser.getAvatar())
                .toJson()
        );
    }
}
