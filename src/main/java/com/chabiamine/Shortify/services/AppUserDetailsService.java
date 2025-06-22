package com.chabiamine.Shortify.services;

import com.chabiamine.Shortify.models.Users;
import com.chabiamine.Shortify.repositories.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public  class AppUserDetailsService implements UserDetailsService {


    private final UsersRepository userRepo;


    public AppUserDetailsService(UsersRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User name not found"));

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();

    }

}
