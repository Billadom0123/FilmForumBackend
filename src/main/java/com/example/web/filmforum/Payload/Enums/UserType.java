package com.example.web.filmforum.Payload.Enums;

import lombok.Getter;

// 用户种类，如有需要也可自行增加修改
@Getter
public enum UserType {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");


    private final String string;
    UserType(String role) {
        this.string = role;
    }

}
