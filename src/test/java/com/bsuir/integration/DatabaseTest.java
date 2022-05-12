package com.bsuir.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
@ActiveProfiles("test")
public class DatabaseTest {

    @Test
    public void testIsConnectionToDatabase (
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String user,
            @Value("${spring.datasource.password}") String password
    ) throws SQLException {
        DriverManager.getConnection(url, user, password);
    }
}
