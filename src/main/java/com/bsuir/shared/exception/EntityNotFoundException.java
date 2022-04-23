package com.bsuir.shared.exception;

public class EntityNotFoundException extends AbstractException {

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException() {
    super("entity_not_found");
  }
}
