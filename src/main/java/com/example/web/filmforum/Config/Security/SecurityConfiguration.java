package com.example.web.filmforum.Config.Security;

import com.alibaba.fastjson2.JSON;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Repository.UserRepository;
import com.example.web.filmforum.Service.Login.UserDetailsServiceImpl;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOriginPatterns(List.of("*"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            corsConfiguration.setAllowedMethods(List.of("*"));
            corsConfiguration.setAllowCredentials(true);
            return corsConfiguration;
        }));
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 静态资源放行
                .requestMatchers("/api/user/**").permitAll()  // user部分接口允许通行
                .requestMatchers("/api/oauth2/**").permitAll()
                .requestMatchers("/api/test/**").permitAll()  // test类接口允许通行（允许任何人测试）
                .requestMatchers("/swagger-ui/**").permitAll()  // 接口文档通行
                .requestMatchers("/v3/api-docs/**").permitAll()  // 同上
                .anyRequest().authenticated()  // 其余访问均需要鉴权
        );
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(2)
        );

        // 登录页
        http.formLogin(login -> login
                .loginProcessingUrl("/api/user/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler())
                // 登录的身份认证失败
                .failureHandler(customAuthenticationFailureHandler())
        );

        http.logout(logout -> logout.logoutUrl("/api/user/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    // 设置返回编码格式，使用PrintWriter方法输出
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    // 设置resp返回状态，成功为200
                    response.setStatus(200);
                    out.write(JSON.toJSONString(DataResponse.ok()));
                    out.flush();
                    out.close();
                }));

        http.rememberMe(rememberMe -> rememberMe
                .key("idontthinkucanezlyguessedithahaha") // key用来和cookie中的值进行匹配,复杂(maybe)且唯一
                .tokenValiditySeconds(60 * 60 * 24 * 7) // 7天
                .rememberMeParameter("remember-me")
                .userDetailsService(userDetailsServiceImpl)
        );

        http.exceptionHandling(ex -> ex
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler())
        );

        return http.build();
    }

    @Bean
    AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            UserPO user = userRepository.findByUsername(authentication.getName());
            userRepository.save(user);
            // 设置返回编码格式，使用PrintWriter方法输出
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            // 设置resp返回状态，成功为200
            response.setStatus(200);
            out.write(JSON.toJSONString(DataResponse.success(
                    H.build()
                            .put("username", user.getUsername())
                            .put("nickname", user.getNickname())
                            .put("email", user.getEmail())
                            .put("role", user.getRole().getString())
                            .put("level", user.getLevel())
                            .put("exp", H.build()
                                    .put("now", user.getExp())
                                    .put("next", user.getLevel() * 100)
                                    .toJson())
                            .put("joinDate", user.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                            .put("avatar", user.getAvatar())
                            .toJson()
            )));
            out.flush();
            out.close();
        };
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return (request, response, exception) -> {
            // 设置返回编码格式，使用PrintWriter方法输出
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            // 设置resp返回状态，失败为401
            response.setStatus(403);
            // 根据错误状态设置返回内容
            DataResponse data;
            if (exception instanceof BadCredentialsException) {
                data = DataResponse.failure(CommonErr.NAME_OR_PASSWORD_WRONG);
            } else {
                data = DataResponse.failure(CommonErr.UNKNOWN_LOGIN_ERROR);
            }
            out.write(JSON.toJSONString(data));
            out.flush();
            out.close();
        };
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            // 设置返回编码格式，使用PrintWriter方法输出
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            // 设置resp返回状态，失败为403
            response.setStatus(403);
            out.write(JSON.toJSONString(DataResponse.failure(CommonErr.NO_AUTHORITY)));
            out.flush();
            out.close();
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            // 设置返回编码格式，使用PrintWriter方法输出
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            // 设置resp返回状态，失败为401
            response.setStatus(401);
            out.write(JSON.toJSONString(DataResponse.failure(CommonErr.NO_AUTHENTICATION)));
            out.flush();
            out.close();
        };
    }
}