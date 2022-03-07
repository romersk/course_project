package com.bsuir.services;

import com.bsuir.entities.UserEvdokimovRD;
import com.bsuir.repositories.UserRepositoryEvdokimovRD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceEvdokimovRD implements UserDetailsService {

    @Autowired
    private UserRepositoryEvdokimovRD repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEvdokimovRD user = repository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден с именем + " + username);
        }
        return user;
    }
}
