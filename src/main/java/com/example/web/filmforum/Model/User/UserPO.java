package com.example.web.filmforum.Model.User;

import com.example.web.filmforum.Payload.Enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

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

    @NotBlank
    @Column(unique = true)
    private String username;

    private String password;

    @Email
    @Column(unique = true)
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
