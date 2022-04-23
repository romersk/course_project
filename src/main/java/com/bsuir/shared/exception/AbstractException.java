package com.bsuir.shared.exception;

public abstract class AbstractException extends RuntimeException {

  AbstractException(String message){
    super(message);
  }
}
