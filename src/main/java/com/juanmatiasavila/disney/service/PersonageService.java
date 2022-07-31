package com.juanmatiasavila.disney.service;


import com.juanmatiasavila.disney.models.Personage;
import com.juanmatiasavila.disney.models.PersonageDTO;
import com.juanmatiasavila.disney.repository.PersonageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonageService {

    @Autowired
    PersonageRepository personageRepository;

    public List<PersonageDTO> findAll() {
        List<PersonageDTO> personageDTOList = personageRepository.findAll().stream()
                .map(personage -> convertToDTO(Optional.of(personage)).get()).collect(Collectors.toList());
        return personageDTOList;
    }
    public Boolean deletePersonage(Integer id){
        return personageRepository.findById(id).map(personage ->
        {personageRepository.delete(personage); return true;}).orElse(false);
    }
    public Boolean validator(Integer id){
        Boolean validatorPersonage = personageRepository.findById(id).isPresent();
        return validatorPersonage;
    }
    public PersonageDTO findById(Integer id) {
        return convertToDTO(personageRepository.findById(id)).get();
    }

    public List<PersonageDTO> findByAge(Integer age, List<PersonageDTO> personageDTOList) {
        if (age == null) {
            return personageDTOList;
        } else {
            List<PersonageDTO> agePersonageDTO = personageDTOList.stream().filter(personageDTO -> personageDTO.getAgePersonage() == age).collect(Collectors.toList());
            return agePersonageDTO;
        }
    }
    public List<PersonageDTO> findByName(String name, List<PersonageDTO> personageDTOList) {
        if (name == null) {
            return personageDTOList;
        } else {
            List<PersonageDTO> namePersonageDTO = personageDTOList.stream().filter(personageDTO -> personageDTO.getNamePersonage().equals(name)).collect(Collectors.toList());
            return namePersonageDTO;
        }
    }
    public List<PersonageDTO> findByMovie(Integer movie, List<PersonageDTO> personageDTOList) {
        if (movie == null) {
            return personageDTOList;
        } else {
            List<PersonageDTO> moviePersonageDTO = personageDTOList.stream().filter(personageDTO -> personageDTO.getMovies().stream().anyMatch(movie1 -> movie1.getIdMovie().equals(movie))).collect(Collectors.toList());
            return moviePersonageDTO;
        }
    }
    public List<PersonageDTO> findByRequest(String name, Integer age, Integer idMovie) {
        List<PersonageDTO> returnDTO = findByMovie(idMovie, findByName(name, findByAge(age, findAll())));
        return shortDTO(returnDTO);
    }
    public PersonageDTO save(PersonageDTO personageDTO) {
        Personage savePersonage = personageRepository.save(convertToModel(Optional.of(personageDTO)).get());
        return convertToDTO(Optional.of(savePersonage)).get();
    }
    public PersonageDTO update(PersonageDTO personageDTO, Integer id) {
        Personage updatePersonage = personageRepository.findById(id)
                .map(personage -> personage.addUpdatePersonage(convertToModel(Optional.of(personageDTO)).get())).get();
        personageRepository.save(updatePersonage);
        return convertToDTO(Optional.of(updatePersonage)).get();
    }
    public Optional<PersonageDTO> convertToDTO(Optional<Personage> personage) {
        Optional<PersonageDTO> personageDTO = Optional.of(new PersonageDTO());
        personageDTO.get().setIdPersonage(personage.get().getIdPersonage());
        personageDTO.get().setNamePersonage(personage.get().getNamePersonage());
        personageDTO.get().setImagePersonage(personage.get().getImagePersonage());
        personageDTO.get().setAgePersonage(personage.get().getAgePersonage());
        personageDTO.get().setWeightPersonage(personage.get().getWeightPersonage());
        personageDTO.get().setStoryPersonage(personage.get().getStoryPersonage());
        personageDTO.get().setMovies(personage.get().getMovies());
        return personageDTO;
    }

    public Optional<Personage> convertToModel(Optional<PersonageDTO> personageDTO) {
        Optional<Personage> personage = Optional.of(new Personage());
        personage.get().setIdPersonage(personageDTO.get().getIdPersonage());
        personage.get().setNamePersonage(personageDTO.get().getNamePersonage());
        personage.get().setImagePersonage(personageDTO.get().getImagePersonage());
        personage.get().setAgePersonage(personageDTO.get().getAgePersonage());
        personage.get().setWeightPersonage(personageDTO.get().getWeightPersonage());
        personage.get().setStoryPersonage(personageDTO.get().getStoryPersonage());
        personage.get().setMovies(personageDTO.get().getMovies());
        return personage;
    }

    public List<PersonageDTO> shortDTO(List<PersonageDTO> personageDTO) {
        List<PersonageDTO> shortListDTO = personageDTO.stream().
                map(personageDTO1 -> {
                    PersonageDTO shortDTO1 = new PersonageDTO();
                    shortDTO1.setImagePersonage(personageDTO1.getImagePersonage());
                    shortDTO1.setNamePersonage(personageDTO1.getNamePersonage());
                    return shortDTO1;
                }).collect(Collectors.toList());
        return shortListDTO;
    }
}