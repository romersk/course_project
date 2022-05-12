package com.bsuir.junit;

import com.bsuir.document.dto.DocumentDtoEvdokimovRD;
import com.bsuir.document.service.DocumentServiceEvdokimovRD;
import com.bsuir.shared.search.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentControllerTest {

    @Autowired
    private DocumentServiceEvdokimovRD service;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canRetrieveByIdWhenExists() {
        DocumentDtoEvdokimovRD entity = service.getById(2L);

        ResponseEntity<DocumentDtoEvdokimovRD> response = restTemplate.getForEntity("/document/2", DocumentDtoEvdokimovRD.class);

        assertTrue(response.getBody().equals(entity));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist() {
        ResponseEntity<DocumentDtoEvdokimovRD> response = restTemplate.getForEntity("/document/5", DocumentDtoEvdokimovRD.class);

        assertNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void canCreateANewEntity() {
        ResponseEntity<DocumentDtoEvdokimovRD> response = restTemplate.postForEntity("/document/",
                new DocumentDtoEvdokimovRD(), DocumentDtoEvdokimovRD.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void listOfEntities() {
        Page<DocumentDtoEvdokimovRD> list = service.getAll(PageRequest.of(0, 1));
        ResponseEntity<Page> response = restTemplate.getForEntity("/document", Page.class);

        assertEquals(response.getBody(), list);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void canIsPutEntity() {
        DocumentDtoEvdokimovRD entity = service.getById(1L);
        entity.setContent("Новый");
        restTemplate.put("/audit", entity);

        DocumentDtoEvdokimovRD entityUpdated = service.getById(1L);

        assertEquals(entityUpdated, entity);
    }

    @Test
    public void canIsDelete() {
        restTemplate.delete("/audit/2");

        DocumentDtoEvdokimovRD entity = service.getById(2L);

        assertNull(entity);
    }

}
