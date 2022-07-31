package com.juanmatiasavila.disney.repository;


import com.juanmatiasavila.disney.models.Personage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonageRepository extends JpaRepository<Personage, Integer> {

    Personage findByIdPersonage(Integer idPersonage);
    List<Personage> findByNamePersonage(String namePersonage);
    List<Personage> findByAgePersonage(Integer agePersonage);
}
