package com.bsuir.audit.controller;

import com.bsuir.audit.dto.AuditDtoEvdokimovRD;
import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.audit.repository.AuditRepositoryEvdokimovRD;
import com.bsuir.audit.request.AuditRequestEvdokimovRD;
import com.bsuir.audit.service.AuditServiceEvdokimovRD;
import com.bsuir.config.AuthorithyEvdokimovRD;
import com.bsuir.process.dto.ProcessDtoEvdokimovRD;
import com.bsuir.process.entity.ProcessEvdokimovRD;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class AuditControllerEvdokimovRD {

    @Autowired
    private AuditServiceEvdokimovRD service;

    @Autowired
    private AuditRepositoryEvdokimovRD repo;

    @Autowired
    private UserServiceEvdokimovRD serviceUser;

    private String getRole(Principal user) {
        UserEvdokimovRD userObj = (UserEvdokimovRD) serviceUser.loadUserByUsername(user.getName());
        return AuthorithyEvdokimovRD.getAuthority(userObj);
    }

    @GetMapping("/audit")
    public ResponseEntity<Page<AuditDtoEvdokimovRD>> findAll(Principal user, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            Page<AuditDtoEvdokimovRD> page = service.getAll(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/audit")
    private ResponseEntity<?> createProcess(Principal user, @RequestBody String request) {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            AuditDtoEvdokimovRD created = service.create(new AuditRequestEvdokimovRD(request));
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/audit/{id}")
    public ResponseEntity<AuditDtoEvdokimovRD> updateProcess(Principal user, @RequestBody String request, @PathVariable Long id) throws ParseException {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            JSONObject jo = new JSONObject(request);
            AuditEvdokimovRD entity = repo.getById(id);
            if (jo.has("dateStart")) {
                entity.setDateStart(new SimpleDateFormat("yyyy-MM-dd").parse(jo.getString("dateStart")));
            }
            if (jo.has("dateEnd")) {
                entity.setDateEnd(new SimpleDateFormat("yyyy-MM-dd").parse(jo.getString("dateEnd")));
            }
            if (jo.has("plan")) {
                entity.setPlan(jo.getString("plan"));
            }
            repo.save(entity);
            AuditDtoEvdokimovRD updatedUser = service.update(new AuditRequestEvdokimovRD(request), id);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/audit/{id}")
    public ResponseEntity<?> getById(Principal user, @PathVariable Long id) {
        String role = getRole(user);
        if (role.equals("ADMIN")) {
            AuditDtoEvdokimovRD auditDtoEvdokimovRD = service.getById(id);
            return new ResponseEntity<>(auditDtoEvdokimovRD, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/audit/{id}")
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
