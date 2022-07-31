package com.juanmatiasavila.disney.controller;

import com.juanmatiasavila.disney.models.Genre;
import com.juanmatiasavila.disney.models.GenreDTO;
import com.juanmatiasavila.disney.service.GenreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    GenreService genreService;


    @GetMapping()
    @ApiOperation("Get all Genres")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<GenreDTO>> getAllGenre() {
        List<GenreDTO> genreDTO = genreService.findAll().stream()
                .map(genre -> genreService.convertToDTO(Optional.of(genre)))
                .collect(Collectors.toList());
        return new ResponseEntity<>(genreDTO, HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation("Create new Genre")
    @ApiResponse(code = 201, message = "CREATED")
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genreDTO) {
        return new ResponseEntity<>(genreService.addGenre(genreDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Genre")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity<GenreDTO> updateGenre(@ApiParam(value = "Id Genre", required = true) @RequestBody Genre genre, @PathVariable("id") Integer id) {
        if (genreService.findById(id).isPresent()) {
            return new ResponseEntity<>(genreService.updateGenre(genre, id), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Genre")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity deleteGenre(@ApiParam(value = "Id Genre", required = true) @PathVariable("id") Integer id) {
        if(genreService.deteleGenre(id).equals(true)){
           return new ResponseEntity<>(HttpStatus.OK);
       }else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       }

}
