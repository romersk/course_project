package com.bsuir.integration;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.audit.repository.AuditRepositoryEvdokimovRD;
import com.bsuir.document.entity.DocumentEvdokimovRD;
import com.bsuir.document.repository.DocumentRepositoryEvdokimovRD;
import com.bsuir.shared.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableSpringDataWebSupport
public class AuditControllerTest {

    private final Long MOCK_ID = 1L;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DocumentRepositoryEvdokimovRD repository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private DocumentEvdokimovRD createDocument(String name) {
        DocumentEvdokimovRD document = new DocumentEvdokimovRD();
        document.setName(name);
        return document;
    }

    @Test
    public void givenDocument_whenAdd_thenStatus201andDocumentReturned() throws Exception {
        DocumentEvdokimovRD documentToSave = new DocumentEvdokimovRD();
        documentToSave.setName("test");

        Mockito.when(repository.save(documentToSave)).thenReturn(createDocument("test"));

        mockMvc.perform(
                post("/document")
                        .content(objectMapper.writeValueAsString(documentToSave))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("test"))
                .andReturn();
    }

    @Test
    public void givenId_whenGetExistingDocument_thenStatus200andDocumentReturned() throws Exception {
        Mockito.when(repository.getById(MOCK_ID)).thenReturn(createDocument("test"));

        mockMvc.perform(
                get("/document/{id}", MOCK_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(MOCK_ID))
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    public void givenId_whenGetNotExistingDocument_thenStatus404anExceptionThrown() throws Exception {
        Mockito.when(repository.getById(MOCK_ID)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(
                get("/document/1"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EntityNotFoundException.class));
    }

    @Test
    public void giveDocument_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
        Mockito.when(repository.getById(MOCK_ID)).thenReturn(createDocument("test"));
        DocumentEvdokimovRD document = createDocument("test-1");
        Mockito.when(repository.save(document)).thenReturn(document);

        mockMvc.perform(
                put("/document/{id}", MOCK_ID)
                        .content(objectMapper.writeValueAsString(document))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("test-1"));
    }

    @Test
    public void givenDocument_whenDeleteDocument_thenStatus200() throws Exception {
        DocumentEvdokimovRD document = createDocument("nameTest");
        Mockito.when(repository.existsById(MOCK_ID)).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(document.getId());

        mockMvc.perform(
                delete("/document/{id}", document.getId()))
                .andExpect(status().isOk());
    }
}
