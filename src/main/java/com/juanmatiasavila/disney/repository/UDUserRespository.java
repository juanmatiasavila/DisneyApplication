package com.juanmatiasavila.disney.repository;

import com.juanmatiasavila.disney.models.UDUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UDUserRespository extends JpaRepository<UDUser,String> {

    Optional<UDUser> findByUsername(String username);

}
