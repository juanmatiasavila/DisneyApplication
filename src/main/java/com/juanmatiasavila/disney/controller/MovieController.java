package com.juanmatiasavila.disney.controller;

import com.juanmatiasavila.disney.models.MovieDTO;
import com.juanmatiasavila.disney.service.MovieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping()
    @ApiOperation("Get all Movies. Make filters with name or genre. Also can get json in ascending order or descending order")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<MovieDTO>> getMovies(@ApiParam(value = "Filter with name", required = false) @RequestParam(required = false) String name,
                                                    @ApiParam(value = "Filter with genre", required = false) @RequestParam(required = false) Integer genre,
                                                    @ApiParam(value = "Order - 'ASC' ascending or 'DESC' descending ", required = false) @RequestParam(required = false) String order) {
        if (name == null && genre == null && order == null){
            return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
        }else
            return new ResponseEntity<>(movieService.findByRequest(name,genre,order), HttpStatus.OK);

    }

    @PostMapping()
    @ApiOperation("Create new Movie with Personage")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST - Id Personage does not exist")
    })
    public ResponseEntity<MovieDTO> addMovieWithPersonage(@RequestBody MovieDTO movieDTO) {
       Boolean personageId = movieDTO.getPersonages().stream().allMatch(personage ->
               movieService.validatorPersonage(personage.getIdPersonage()));
       if (personageId){
           return new ResponseEntity<>(movieService.addMovieWithPersonage(movieDTO), HttpStatus.CREATED);
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{idMovie}/personages/{idPersonage}")
    @ApiOperation("Create relationship of movies with their personages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity<MovieDTO> addPersonageFromMovie(@ApiParam(value = "Id Movie", required = true) @PathVariable("idMovie") Integer idMovie,
                                                          @ApiParam(value = "Id Personage", required = true)@PathVariable("idPersonage") Integer idPersonage) {
        if (movieService.validatorMovie(idMovie) && movieService.validatorPersonage(idPersonage)) {
            return new ResponseEntity<>(movieService.addPersonageFromMovie(idMovie, idPersonage), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(new MovieDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Movie")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity<MovieDTO> updateMovie(@ApiParam(value = "Id Movie", required = true) @PathVariable("id") Integer id, @RequestBody MovieDTO movieDTO) {
        if (movieService.validatorMovie(id)) {
        return new ResponseEntity<>(movieService.update(movieDTO, id), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(new MovieDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{idMovie}/personages/{idPersonage}")
    @ApiOperation("Delete relationship of movies with their personages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity<MovieDTO> deletePersonageFromMovie(@ApiParam(value = "Id Movie", required = true) @PathVariable("idMovie") Integer idMovie,
                                                             @ApiParam(value = "Id Personage", required = true)@PathVariable("idPersonage") Integer idPersonage) {
        if (movieService.validatorMovie(idMovie) && movieService.validatorPersonage(idPersonage)) {
            return new ResponseEntity<>(movieService.deletePersonageFromMovie(idMovie, idPersonage), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new MovieDTO(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    @ApiOperation("Delete Movie")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity deleteGenre(@ApiParam(value = "Id Movie", required = true) @PathVariable("id") Integer id) {
        if (movieService.deleteMovie(id).equals(true)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}