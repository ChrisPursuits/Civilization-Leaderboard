package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.dto.RegisterUserDto;
import com.example.civilizationleaderboard.repository.impl.JdbcUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDetailsManager userDetailsManager;
    private PasswordEncoder passwordEncoder;
    private JdbcUser jdbcUser;

    public UserService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, JdbcUser jdbcUser) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.jdbcUser = jdbcUser;
    }

    public void registerUser(RegisterUserDto newUser) {
        UserDetails userToRegister = User.builder()
                .username(newUser.username())
                .password(passwordEncoder.encode(newUser.password())).roles("USER")
                .build();

        userDetailsManager.createUser(userToRegister);
    }
}
