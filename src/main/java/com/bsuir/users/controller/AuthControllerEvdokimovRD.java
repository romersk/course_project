package com.bsuir.users.controller;

import com.bsuir.config.JWTTokenHelperEvdokimovRD;
import com.bsuir.users.dto.UsersDtoEvdokimovRD;
import com.bsuir.users.entity.AuthorityEvdokimovRD;
import com.bsuir.users.entity.PersonEvdokimovRD;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.bsuir.users.request.AuthenticationRequestEvdokimovRD;
import com.bsuir.responses.LoginResponseEvdokimovRD;
import com.bsuir.users.request.UserRequestEvdokimovRD;
import com.bsuir.users.service.UserServiceEvdokimovRD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthControllerEvdokimovRD {

    private static Logger log = LoggerFactory.getLogger(AuthControllerEvdokimovRD.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelperEvdokimovRD jwtTokenUtil;

    @Autowired
    private UserServiceEvdokimovRD service;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestEvdokimovRD authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword()));

        final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getUserName());

        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        LoginResponseEvdokimovRD loginResponseEvdokimovRD = new LoginResponseEvdokimovRD();
        loginResponseEvdokimovRD.setToken(token);
        loginResponseEvdokimovRD.setUsername(userDetails.getUsername());

        UserEvdokimovRD userObj = (UserEvdokimovRD) service.loadUserByUsername(loginResponseEvdokimovRD.getUsername());
        loginResponseEvdokimovRD.setFio(userObj.getPerson().getFirstName() + " " + userObj.getPerson().getSecondName());

        List<AuthorityEvdokimovRD> authorithies = (List<AuthorityEvdokimovRD>) userObj.getAuthorities();
        if ("ADMIN".equals(authorithies.get(0).getRoleCode())) {
            loginResponseEvdokimovRD.setRole("Администратор");
        } else if ("EXPERT".equals(authorithies.get(0))) {
            loginResponseEvdokimovRD.setRole("Эксперт");
        } else if ("LAWYER".equals(authorithies.get(0))) {
            loginResponseEvdokimovRD.setRole("Юрист");
        } else {
            loginResponseEvdokimovRD.setRole("Пользователь");
        }
        loginResponseEvdokimovRD.setId(userObj.getId());
        return ResponseEntity.ok(loginResponseEvdokimovRD);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        UserEvdokimovRD userObj = (UserEvdokimovRD) service.loadUserByUsername(user.getName());

        PersonEvdokimovRD personEvdokimovRD = new PersonEvdokimovRD();
        personEvdokimovRD.setFirstName(userObj.getPerson().getFirstName());
        personEvdokimovRD.setSecondName(userObj.getPerson().getSecondName());

        return ResponseEntity.ok(userObj);
    }

    @PostMapping(value = "/auth/register")
    public ResponseEntity<?> register(@RequestBody String request) {
        log.info("AuthControllerEvdokimovRD : register");
        UsersDtoEvdokimovRD usersDtoEvdokimovRD = service.create(new UserRequestEvdokimovRD(request));
        return new ResponseEntity<>(usersDtoEvdokimovRD, HttpStatus.CREATED);
    }
}
