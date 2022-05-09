package com.bsuir.document.validator;

import com.bsuir.document.entity.DocumentEvdokimovRD;
import com.bsuir.shared.validation.Error;
import com.bsuir.shared.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentValidatorEvdokimovRD implements Validator<DocumentEvdokimovRD> {
    @Override
    public List<Error> validate(DocumentEvdokimovRD entity) {
        return new ArrayList<>();
    }
}
