package com.bsuir.process.controller;

import com.bsuir.config.AuthorithyEvdokimovRD;
import com.bsuir.process.dto.ProcessDtoEvdokimovRD;
import com.bsuir.process.entity.ProcessEvdokimovRD;
import com.bsuir.process.repository.ProcessRepositoryEvdokimovRD;
import com.bsuir.process.request.ProcessRequestEvdokimovRD;
import com.bsuir.process.service.ProcessServiceEvdokimovRD;
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
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ProcessControllerEvdokimovRD {

    @Autowired
    private ProcessServiceEvdokimovRD service;

    @Autowired
    private ProcessRepositoryEvdokimovRD repo;

    @Autowired
    private UserServiceEvdokimovRD serviceUser;

    private String getRole(Principal user) {
        UserEvdokimovRD userObj = (UserEvdokimovRD) serviceUser.loadUserByUsername(user.getName());
        return AuthorithyEvdokimovRD.getAuthority(userObj);
    }

    @GetMapping("/process/userId/{id}")
    public ResponseEntity<Page<ProcessDtoEvdokimovRD>> findByUserId(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable, @PathVariable Long id) {
        String role = getRole(user);
        if (role.equals("USER")) {
            Page<ProcessDtoEvdokimovRD> page = service.getByIdUser(pageable, id);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/process/user/{id}")
    public ResponseEntity<Page<ProcessDtoEvdokimovRD>> findAll(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable, @PathVariable Long id) {
        String role = getRole(user);
        if (role.equals("USER")) {
            Page<ProcessDtoEvdokimovRD> page = service.getById(pageable, id);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/process")
    public ResponseEntity<Page<ProcessDtoEvdokimovRD>> findAll(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            Page<ProcessDtoEvdokimovRD> page = service.getAll(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/process/stage/1")
    public ResponseEntity<List<ProcessDtoEvdokimovRD>> findStageOne(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        String role = getRole(user);
        if (role.equals("EXPERT")) {
            List<ProcessDtoEvdokimovRD> page = service.getByStageOne(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/process/stage/2")
    public ResponseEntity<List<ProcessDtoEvdokimovRD>> findStageTwo(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        String role = getRole(user);
        if (role.equals("EXPERT")) {
            List<ProcessDtoEvdokimovRD> page = service.getByStageTwo(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/process/stage/3")
    public ResponseEntity<List<ProcessDtoEvdokimovRD>> findStageThree(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        String role = getRole(user);
        if (role.equals("EXPERT")) {
            List<ProcessDtoEvdokimovRD> page = service.getByStageThree(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/process/stage/4")
    public ResponseEntity<List<ProcessDtoEvdokimovRD>> findStageFour(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        String role = getRole(user);
        if (role.equals("LAWYER")) {
            List<ProcessDtoEvdokimovRD> page = service.getByStageFour(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }


    @PostMapping("/process")
    private ResponseEntity<?> createProcess(Principal user, @RequestBody String request) {
        String role = getRole(user);
        if (role.equals("ADMIN") || role.equals("USER")) {
            ProcessDtoEvdokimovRD created = service.create(new ProcessRequestEvdokimovRD(request));
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/process/{id}")
    public ResponseEntity<ProcessDtoEvdokimovRD> updateProcess(Principal user, @RequestBody String request, @PathVariable Long id) {
        String role = getRole(user);
        if (role.equals("ADMIN") || role.equals("EXPERT") || role.equals("LAWYER")) {
            JSONObject jo = new JSONObject(request);
            ProcessEvdokimovRD entity = repo.getById(id);
            if (jo.has("status")) {
                entity.setStatus(jo.getString("status"));
            }
            if (jo.has("recommendations")) {
                entity.setRecommendations(jo.getString("recommendations"));
            }
            repo.save(entity);
            ProcessDtoEvdokimovRD updatedUser = service.update(new ProcessRequestEvdokimovRD(request), id);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/process/{id}")
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
