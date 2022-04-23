package com.bsuir.users.controller;

import com.bsuir.config.JWTTokenHelperEvdokimovRD;
import com.bsuir.shared.search.Page;
import com.bsuir.users.dto.UsersDtoEvdokimovRD;
import com.bsuir.users.entity.AuthorityEvdokimovRD;
import com.bsuir.users.entity.PersonEvdokimovRD;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.bsuir.users.request.AuthenticationRequestEvdokimovRD;
import com.bsuir.responses.LoginResponseEvdokimovRD;
import com.bsuir.users.service.UserServiceEvdokimovRD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

        return ResponseEntity.ok(loginResponseEvdokimovRD);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        UserEvdokimovRD userObj = (UserEvdokimovRD) service.loadUserByUsername(user.getName());

        PersonEvdokimovRD personEvdokimovRD = new PersonEvdokimovRD();
        personEvdokimovRD.setFirstName(userObj.getPerson().getFirstName());
        personEvdokimovRD.setSecondName(userObj.getPerson().getSecondName());

        return ResponseEntity.ok(personEvdokimovRD);
    }

    @GetMapping("/auth/users")
    public ResponseEntity<Page<UsersDtoEvdokimovRD>> findAll(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        UserEvdokimovRD userObj = (UserEvdokimovRD) service.loadUserByUsername(user.getName());
        List<AuthorityEvdokimovRD> auth = (List<AuthorityEvdokimovRD>) userObj.getAuthorities();
        if (auth.get(0).getRoleCode().equals("ADMIN")) {
            Page<UsersDtoEvdokimovRD> page = service.getAll(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
}
