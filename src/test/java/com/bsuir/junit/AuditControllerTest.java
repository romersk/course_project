package com.bsuir.junit;


import com.bsuir.audit.dto.AuditDtoEvdokimovRD;
import com.bsuir.audit.service.AuditServiceEvdokimovRD;
import com.bsuir.shared.search.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuditControllerTest {

    @Autowired
    private AuditServiceEvdokimovRD service;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canRetrieveByIdWhenExists() {
        AuditDtoEvdokimovRD entity = service.getById(2L);

        ResponseEntity<AuditDtoEvdokimovRD> response = restTemplate.getForEntity("/audit/2", AuditDtoEvdokimovRD.class);

        assertTrue(response.getBody().equals(entity));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist() {
        ResponseEntity<AuditDtoEvdokimovRD> response = restTemplate.getForEntity("/audit/5", AuditDtoEvdokimovRD.class);

        assertNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void canCreateANewEntity() {
        ResponseEntity<AuditDtoEvdokimovRD> response = restTemplate.postForEntity("/audit/",
                new AuditDtoEvdokimovRD(), AuditDtoEvdokimovRD.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void listOfEntities() {
        Page<AuditDtoEvdokimovRD> list = service.getAll(PageRequest.of(0, 1));
        ResponseEntity<Page> response = restTemplate.getForEntity("/audit", Page.class);

        assertEquals(response.getBody(), list);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void canIsPutEntity() {
        AuditDtoEvdokimovRD entity = service.getById(1L);
        entity.setPlan("Новый");
        restTemplate.put("/audit", entity);

        AuditDtoEvdokimovRD entityUpdated = service.getById(1L);

        assertEquals(entityUpdated, entity);
    }

    @Test
    public void canIsDelete() {
        restTemplate.delete("/audit/2");

        AuditDtoEvdokimovRD entity = service.getById(2L);

        assertNull(entity);
    }

    @Test
    public void canIsByAnotherEntity() {
        ResponseEntity<AuditDtoEvdokimovRD> response = restTemplate.getForEntity("/audit/process/2", AuditDtoEvdokimovRD.class);

        AuditDtoEvdokimovRD entity = service.getAuditByProcess(2L);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), entity);
    }
}
