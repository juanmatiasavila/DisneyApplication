package com.juanmatiasavila.disney.service;

import com.juanmatiasavila.disney.models.UDUser;
import com.juanmatiasavila.disney.repository.UDUserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DisneyUserDetailService implements UserDetailsService {

    @Autowired
    UDUserRespository udUserRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UDUser user = udUserRespository.findByUsername(username).get();
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
