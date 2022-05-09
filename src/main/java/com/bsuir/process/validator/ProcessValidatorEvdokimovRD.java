package com.bsuir.process.validator;

import com.bsuir.process.entity.ProcessEvdokimovRD;
import com.bsuir.shared.validation.Error;
import com.bsuir.shared.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessValidatorEvdokimovRD implements Validator<ProcessEvdokimovRD> {
    @Override
    public List<Error> validate(ProcessEvdokimovRD entity) {
        return new ArrayList<>();
    }
}
