package com.bsuir.junit;

import com.bsuir.audit.dto.AuditDtoEvdokimovRD;
import com.bsuir.process.dto.ProcessDtoEvdokimovRD;
import com.bsuir.process.service.ProcessServiceEvdokimovRD;
import com.bsuir.shared.search.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {

    @Autowired
    private ProcessServiceEvdokimovRD service;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canRetrieveByIdWhenExists() {
        ProcessDtoEvdokimovRD entity = service.getById(2L);

        ResponseEntity<ProcessDtoEvdokimovRD> response = restTemplate.getForEntity("/process/2", ProcessDtoEvdokimovRD.class);

        assertTrue(response.getBody().equals(entity));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist() {
        ResponseEntity<ProcessDtoEvdokimovRD> response = restTemplate.getForEntity("/process/5", ProcessDtoEvdokimovRD.class);

        assertNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void canCreateANewEntity() {
        ResponseEntity<ProcessDtoEvdokimovRD> response = restTemplate.postForEntity("/process/",
                new ProcessDtoEvdokimovRD(), ProcessDtoEvdokimovRD.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void listOfEntities() {
        Page<ProcessDtoEvdokimovRD> list = service.getAll(PageRequest.of(0, 1));
        ResponseEntity<Page> response = restTemplate.getForEntity("/process", Page.class);

        assertEquals(response.getBody(), list);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void canIsPutEntity() {
        ProcessDtoEvdokimovRD entity = service.getById(1L);
        entity.setName("Новый");
        restTemplate.put("/process", entity);

        ProcessDtoEvdokimovRD entityUpdated = service.getById(1L);

        assertEquals(entityUpdated, entity);
    }

    @Test
    public void canIsDelete() {
        restTemplate.delete("/process/2");

        ProcessDtoEvdokimovRD entity = service.getById(2L);

        assertNull(entity);
    }

    @Test
    public void canIsByAnotherEntity() {
        ResponseEntity<Page> response = restTemplate.getForEntity("/process/userId/2", Page.class);

        Page<ProcessDtoEvdokimovRD> entity = service.getByIdUser(PageRequest.of(0, 5), 2L);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), entity);
    }

    @Test
    public void canIsFindByStageOne() {
        ResponseEntity<List> response = restTemplate.getForEntity("/process/stage/1", List.class);

        List<ProcessDtoEvdokimovRD> entity = service.getByStageOne(PageRequest.of(0, 5));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), entity);
    }

    @Test
    public void canIsFindByStageTwo() {
        ResponseEntity<List> response = restTemplate.getForEntity("/process/stage/2", List.class);

        List<ProcessDtoEvdokimovRD> entity = service.getByStageTwo(PageRequest.of(0, 5));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), entity);
    }

    @Test
    public void canIsFindByStageThree() {
        ResponseEntity<List> response = restTemplate.getForEntity("/process/stage/3", List.class);

        List<ProcessDtoEvdokimovRD> entity = service.getByStageThree(PageRequest.of(0, 5));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), entity);
    }

    @Test
    public void canIsFindByStageFour() {
        ResponseEntity<List> response = restTemplate.getForEntity("/process/stage/4", List.class);

        List<ProcessDtoEvdokimovRD> entity = service.getByStageFour(PageRequest.of(0, 5));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), entity);
    }
}
