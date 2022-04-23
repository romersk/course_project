package com.bsuir.shared.exception;

import java.util.List;
import com.bsuir.shared.validation.Error;

public class ValidationException extends AbstractException {

  private List<Error> errorList;

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(List<Error> errorList) {
    super("");
    this.errorList = errorList;
  }

  public List<Error> getErrorList() {
    return errorList;
  }
}
