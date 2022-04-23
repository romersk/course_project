package com.bsuir.users.validator;

import com.bsuir.shared.validation.Error;
import com.bsuir.shared.validation.Validator;
import com.bsuir.users.entity.UserEvdokimovRD;

import java.util.ArrayList;
import java.util.List;

public class UsersValidatorEvdokimovRD implements Validator<UserEvdokimovRD> {
    @Override
    public List<Error> validate(UserEvdokimovRD entity) {
        List<Error> errorList = new ArrayList<>();
        errorList.addAll(validateFirstAndLastName(entity));
        errorList.addAll(validateEmail(entity));
        return errorList;
    }

    private List<Error> validateFirstAndLastName(UserEvdokimovRD entity) {
        List<Error> errorList = new ArrayList<>();

        if (entity.getPerson().getFirstName() == null) {
            errorList.add(Error.fieldValidationError("first_name", "required"));
        } else if (entity.getPerson().getFirstName().length() > 100) {
            errorList.add(Error.fieldValidationError("first_name", "max_length_55"));
        }

        if (entity.getPerson().getSecondName() == null) {
            errorList.add(Error.fieldValidationError("second_name", "required"));
        } else if (entity.getPerson().getSecondName().length() > 100) {
            errorList.add(Error.fieldValidationError("second_name", "max_length_55"));
        }

        return errorList;
    }

    private List<Error> validateEmail(UserEvdokimovRD entity) {
        List<Error> errorList = new ArrayList<>();

        if (entity.getPerson().getEmail() == null) {
            errorList.add(Error.fieldValidationError("email", "required"));
        }

        return errorList;
    }
}
