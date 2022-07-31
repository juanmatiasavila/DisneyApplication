package com.juanmatiasavila.disney.controller;


import com.juanmatiasavila.disney.models.PersonageDTO;
import com.juanmatiasavila.disney.service.PersonageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/personage")
public class PersonageController {

    @Autowired
    private PersonageService personageService;

    @GetMapping("/{id}")
    @ApiOperation("Get Personage with Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity<PersonageDTO> getPersonageById(@ApiParam(value = "Id Personage", required = true)
            @PathVariable("id") Integer id) {
        if (personageService.validator(id)) {
            return new ResponseEntity<>(personageService.findById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new PersonageDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    @ApiOperation("Get all Personages or make filters with params name, age or idMovie")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<PersonageDTO>> getPersonageByRequest(@ApiParam(value = "Filter with name", required = false) @RequestParam(required = false) String name,
                                                    @ApiParam(value = "Filter with age", required = false) @RequestParam(required = false) Integer age,
                                                    @ApiParam(value = "Filter with IdMovie", required = false) @RequestParam(required = false) Integer idMovie) {
    if (name == null && age == null && idMovie == null){
        return new ResponseEntity<>(personageService.findAll(), HttpStatus.OK);
    }else
        return new ResponseEntity<>(personageService.findByRequest(name,age,idMovie), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation("Save new personage")
    @ApiResponse(code = 201, message = "CREATED")
   public ResponseEntity<PersonageDTO> addPersonage(@RequestBody PersonageDTO personageDTO){
        return new ResponseEntity<>(personageService.save(personageDTO), HttpStatus.CREATED);
    }

   @PutMapping("/{id}")
   @ApiOperation("Update personage")
   @ApiResponses({
           @ApiResponse(code = 200, message = "OK"),
           @ApiResponse(code = 400, message = "BAD_REQUEST")
   })
    public ResponseEntity<PersonageDTO> updatePersonage(@ApiParam(value = "The id of the personage", required = true)
                                                           @PathVariable("id") Integer id, @RequestBody PersonageDTO personageDTO){
       if (personageService.validator(id)){
        return new ResponseEntity<>(personageService.update(personageDTO, id), HttpStatus.CREATED);
   }else {
        return new ResponseEntity<>(new PersonageDTO(), HttpStatus.BAD_REQUEST);
    }
   }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Genre")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity deletePersonage(@ApiParam(value = "Id Personage", required = true) @PathVariable("id") Integer id) {
        if (personageService.deletePersonage(id).equals(true)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
