package com.bsuir.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.bsuir.shared.validation.Error;

public class ErrorTest {

    @Test
    public void testFieldValidationError() {
        Object error = Error.fieldValidationError("errorName", "errorCode");
        Assertions.assertEquals(error.getClass(), Error.class);
    }
}
