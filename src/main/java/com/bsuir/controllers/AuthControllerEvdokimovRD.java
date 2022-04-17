package com.bsuir.controllers;

import com.bsuir.config.JWTTokenHelperEvdokimovRD;
import com.bsuir.entities.PersonEvdokimovRD;
import com.bsuir.entities.UserEvdokimovRD;
import com.bsuir.requests.AuthenticationRequestEvdokimovRD;
import com.bsuir.responses.LoginResponseEvdokimovRD;
import com.bsuir.services.UserServiceEvdokimovRD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthControllerEvdokimovRD {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelperEvdokimovRD jwtTokenUtil;

    @Autowired
    private UserServiceEvdokimovRD userDetailsService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestEvdokimovRD authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        LoginResponseEvdokimovRD loginResponseEvdokimovRD = new LoginResponseEvdokimovRD();
        loginResponseEvdokimovRD.setToken(token);
        return ResponseEntity.ok(loginResponseEvdokimovRD);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        UserEvdokimovRD userObj = (UserEvdokimovRD) userDetailsService.loadUserByUsername(user.getName());

        PersonEvdokimovRD personEvdokimovRD = new PersonEvdokimovRD();
        personEvdokimovRD.setFirstName(userObj.getPerson().getFirstName());
        personEvdokimovRD.setSecondName(userObj.getPerson().getSecondName());

        return ResponseEntity.ok(personEvdokimovRD);
    }
}
