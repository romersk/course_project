package com.bsuir.document.dto.mapper;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.document.entity.DocumentEvdokimovRD;
import com.bsuir.document.request.DocumentRequestEvdokimovRD;
import com.bsuir.shared.exception.BadRequestException;
import com.bsuir.shared.mapping.CreateDtoMapper;
import com.bsuir.shared.mapping.UpdateDtoMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DocumentDtoMapperEvdokimovRD implements UpdateDtoMapper<DocumentRequestEvdokimovRD, DocumentEvdokimovRD>, CreateDtoMapper<DocumentRequestEvdokimovRD, DocumentEvdokimovRD> {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public DocumentEvdokimovRD mapToEntity(DocumentRequestEvdokimovRD request) {
        DocumentEvdokimovRD employee;
        try {
            employee = mapper.readValue(request.getJsonRequest(), DocumentEvdokimovRD.class);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new BadRequestException("bad_request_exception");
        }
        return employee;
    }

    @Override
    public DocumentEvdokimovRD mapToEntity(DocumentEvdokimovRD entity, DocumentRequestEvdokimovRD request) {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        DocumentEvdokimovRD employee;
        try {
            employee = mapper.updateValue(entity, request);
        } catch (IOException exception) {
            throw new BadRequestException("bad_request_exception");
        }
        return employee;
    }
}
