package com.bsuir.process.dto.mapper;

import com.bsuir.process.entity.ProcessEvdokimovRD;
import com.bsuir.process.request.ProcessRequestEvdokimovRD;
import com.bsuir.shared.exception.BadRequestException;
import com.bsuir.shared.mapping.CreateDtoMapper;
import com.bsuir.shared.mapping.UpdateDtoMapper;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProcessDtoMapperEvdokimovRD implements UpdateDtoMapper<ProcessRequestEvdokimovRD, ProcessEvdokimovRD>, CreateDtoMapper<ProcessRequestEvdokimovRD, ProcessEvdokimovRD> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public ProcessEvdokimovRD mapToEntity(ProcessRequestEvdokimovRD request) {
        ProcessEvdokimovRD employee;
        try {
            employee = mapper.readValue(request.getJsonRequest(), ProcessEvdokimovRD.class);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new BadRequestException("bad_request_exception");
        }
        return employee;
    }

    @Override
    public ProcessEvdokimovRD mapToEntity(ProcessEvdokimovRD entity, ProcessRequestEvdokimovRD request) {
        //mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ProcessEvdokimovRD employee;
        try {
            employee = mapper.updateValue(entity, request);
        } catch (IOException exception) {
            throw new BadRequestException("bad_request_exception");
        }
        return employee;
    }
}
