package com.example.web.filmforum.Model.User;

import com.example.web.filmforum.Payload.Enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    private String avatar;

    @NotBlank
    private String nickname;

    private Integer level;

    private Integer exp;

    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    private UserType role;
}
