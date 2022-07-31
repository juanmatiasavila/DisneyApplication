package com.juanmatiasavila.disney.service;


import com.juanmatiasavila.disney.models.UDUser;
import com.juanmatiasavila.disney.repository.UDUserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UDUserService {

    @Autowired
    UDUserRespository udUserRespository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public Boolean registrerUser(UDUser udUser){
    if (udUserRespository.findByUsername(udUser.getUsername()).isPresent()){
        return false;
    }else {
        UDUser user = new UDUser();
        user.setUsername(udUser.getUsername());
        user.setPassword(passwordEncoder.encode(udUser.getPassword()));
        user.setEmail(udUser.getEmail());
        udUserRespository.save(user);
    return true;
    }
    }
}
