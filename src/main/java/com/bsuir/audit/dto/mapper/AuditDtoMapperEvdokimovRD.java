package com.bsuir.audit.dto.mapper;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.audit.request.AuditRequestEvdokimovRD;
import com.bsuir.process.entity.ProcessEvdokimovRD;
import com.bsuir.shared.exception.BadRequestException;
import com.bsuir.shared.mapping.CreateDtoMapper;
import com.bsuir.shared.mapping.UpdateDtoMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuditDtoMapperEvdokimovRD implements UpdateDtoMapper<AuditRequestEvdokimovRD, AuditEvdokimovRD>, CreateDtoMapper<AuditRequestEvdokimovRD, AuditEvdokimovRD> {

    @Autowired
    private ObjectMapper mapper;


    @Override
    public AuditEvdokimovRD mapToEntity(AuditRequestEvdokimovRD request) {
        AuditEvdokimovRD employee;
        try {
            employee = mapper.readValue(request.getJsonRequest(), AuditEvdokimovRD.class);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new BadRequestException("bad_request_exception");
        }
        return employee;
    }

    @Override
    public AuditEvdokimovRD mapToEntity(AuditEvdokimovRD entity, AuditRequestEvdokimovRD request) {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        AuditEvdokimovRD employee;
        try {
            employee = mapper.updateValue(entity, request);
        } catch (IOException exception) {
            throw new BadRequestException("bad_request_exception");
        }
        return employee;
    }
}
