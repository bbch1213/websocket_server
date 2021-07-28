package com.example.websocket_server.user.service;

import com.example.websocket_server.user.entity.LoginValue;
import com.example.websocket_server.user.entity.User;
import com.example.websocket_server.user.entity.UserInfo;
import com.example.websocket_server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public LoginValue checkUser(UserInfo userInfo)
    {
        UserDetails user;
        try{
            user = loadUserByUsername(userInfo.getEmail());
        }catch (Exception e)
        {
            return LoginValue.ID;
        }

        if(user.getPassword().equals(userInfo.getPassword()))
        {
            return LoginValue.PASS;
        }
        else
        {
            return LoginValue.PW;
        }

    }

    public Long save(UserInfo userInfo) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        System.out.println(userInfo.getPassword());

        return userRepository.save(User.builder()
                .email(userInfo.getEmail())
                .auth(userInfo.getAuth())
                .password(userInfo.getPassword()).build()).getCode();
    }
}
