package com.bsuir.users;

import com.bsuir.users.entity.UserEvdokimovRD;
import com.bsuir.users.repository.UserRepositoryEvdokimovRD;
import com.bsuir.users.service.UserServiceEvdokimovRD;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsersTest {

    @Autowired
    private UserRepositoryEvdokimovRD repository;

    @Test
    public void convertToJsonString() throws JsonProcessingException {
        UserEvdokimovRD user = repository.getById(1L);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
    }
}
