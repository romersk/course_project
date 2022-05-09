package com.bsuir.audit.validator;

import com.bsuir.audit.entity.AuditEvdokimovRD;
import com.bsuir.shared.validation.Error;
import com.bsuir.shared.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuditValidatorEvdokimovRD implements Validator<AuditEvdokimovRD> {
    @Override
    public List<Error> validate(AuditEvdokimovRD entity) {
        return new ArrayList<>();
    }
}
