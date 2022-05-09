package com.bsuir.document.controller;

import com.bsuir.config.AuthorithyEvdokimovRD;
import com.bsuir.document.dto.DocumentDtoEvdokimovRD;
import com.bsuir.document.entity.DocumentEvdokimovRD;
import com.bsuir.document.repository.DocumentRepositoryEvdokimovRD;
import com.bsuir.document.request.DocumentRequestEvdokimovRD;
import com.bsuir.document.service.DocumentServiceEvdokimovRD;
import com.bsuir.shared.search.Page;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.bsuir.users.service.UserServiceEvdokimovRD;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentControllerEvdokimovRD {

    @Autowired
    private DocumentServiceEvdokimovRD service;

    @Autowired
    private DocumentRepositoryEvdokimovRD repo;

    @Autowired
    private UserServiceEvdokimovRD serviceUser;

    private String getRole(Principal user) {
        UserEvdokimovRD userObj = (UserEvdokimovRD) serviceUser.loadUserByUsername(user.getName());
        return AuthorithyEvdokimovRD.getAuthority(userObj);
    }

    @GetMapping("/document")
    public ResponseEntity<Page<DocumentDtoEvdokimovRD>> findAll(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            Page<DocumentDtoEvdokimovRD> page = service.getAll(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/document")
    private ResponseEntity<?> createProcess(Principal user, @RequestBody String request) {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            DocumentDtoEvdokimovRD created = service.create(new DocumentRequestEvdokimovRD(request));
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/document/{id}")
    public ResponseEntity<DocumentDtoEvdokimovRD> updateProcess(Principal user, @RequestBody String request, @PathVariable Long id) throws ParseException {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            JSONObject jo = new JSONObject(request);
            DocumentEvdokimovRD entity = repo.getById(id);
            if (jo.has("name")) {
                entity.setName(jo.getString("name"));
            }
            if (jo.has("content")) {
                entity.setContent(jo.getString("content"));
            }
            repo.save(entity);
            DocumentDtoEvdokimovRD updatedUser = service.update(new DocumentRequestEvdokimovRD(request), id);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/document/{id}")
    public ResponseEntity<?> getById(Principal user, @PathVariable Long id) {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            DocumentDtoEvdokimovRD auditDtoEvdokimovRD = service.getById(id);
            return new ResponseEntity<>(auditDtoEvdokimovRD, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/document/{id}")
    public ResponseEntity<?> deleteProcess(Principal user, @PathVariable Long id) {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
}
