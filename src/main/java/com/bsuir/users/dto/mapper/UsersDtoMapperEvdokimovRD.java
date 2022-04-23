package com.bsuir.users.dto.mapper;

import com.bsuir.shared.exception.BadRequestException;
import com.bsuir.shared.mapping.CreateDtoMapper;
import com.bsuir.shared.mapping.UpdateDtoMapper;
import com.bsuir.users.entity.UserEvdokimovRD;
import com.bsuir.users.request.UserRequestEvdokimovRD;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UsersDtoMapperEvdokimovRD implements UpdateDtoMapper<UserRequestEvdokimovRD, UserEvdokimovRD>, CreateDtoMapper<UserRequestEvdokimovRD, UserEvdokimovRD> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public UserEvdokimovRD mapToEntity(UserRequestEvdokimovRD request) {
        UserEvdokimovRD employee;
        try {
            employee = mapper.readValue(request.getJsonRequest(), UserEvdokimovRD.class);
        } catch (IOException exception) {
            throw new BadRequestException("bad_request_exception");
        }
        return employee;
    }

    @Override
    public UserEvdokimovRD mapToEntity(UserEvdokimovRD entity, UserRequestEvdokimovRD request) {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        UserEvdokimovRD employee;
        try {
            employee = mapper.updateValue(entity, request);
        } catch (IOException exception) {
            throw new BadRequestException("bad_request_exception");
        }
        return employee;
    }
}
