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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can't find any User with name: " + username);
        }
        return new UserDetailsImpl(user);
    }
}
