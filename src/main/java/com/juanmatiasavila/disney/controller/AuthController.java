package com.juanmatiasavila.disney.controller;

import com.juanmatiasavila.disney.authentication.AuthenticationRequest;
import com.juanmatiasavila.disney.authentication.AuthenticationResponse;
import com.juanmatiasavila.disney.models.UDUser;
import com.juanmatiasavila.disney.security.JWTUtil;
import com.juanmatiasavila.disney.service.DisneyUserDetailService;
import com.juanmatiasavila.disney.service.UDUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DisneyUserDetailService disneyUserDetailService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    UDUserService udUserService;

    @PostMapping("/login")
    @ApiOperation("Create TOKEN")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 403, message = "FORBIDDEN")
    })
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = disneyUserDetailService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.geretateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/register")
    @ApiOperation("Register username, password and email")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    public ResponseEntity<?> register(@RequestBody UDUser udUser) throws Exception {
        if (udUserService.registrerUser(udUser)) {
            return new ResponseEntity<>(udUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("The username already exist",HttpStatus.BAD_REQUEST);
        }
    }
}
