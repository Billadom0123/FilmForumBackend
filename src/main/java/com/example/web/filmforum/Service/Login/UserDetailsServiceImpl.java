package com.example.web.filmforum.Service.Login;


import com.example.web.filmforum.Model.User.UserDetailsImpl;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        UserPO user;
        if (input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            user = userRepository.findByEmail(input);
        } else {
            user = userRepository.findByUsername(input);
        }
        if (user == null) {
            throw new UsernameNotFoundException("Can't find any User with name or email: " + input);
        }
        return new UserDetailsImpl(user);
    }
}
