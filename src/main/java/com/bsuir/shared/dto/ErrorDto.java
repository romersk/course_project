package com.bsuir.shared.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"code", "message", "trace"})
public class ErrorDto implements Dto {

    private final int code;
    private final String message;
    @JsonInclude(Include.NON_NULL)
    private final String trace;

    public ErrorDto(HttpStatus code, String message, String trace) {
        this.code = code.value();
        this.message = message;
        this.trace = trace;
    }

    public ErrorDto(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
        this.trace = null;
    }

    @JsonGetter("code")
    public int getCode() {
        return code;
    }

    @JsonGetter("message")
    public String getMessage() {
        return message;
    }

    @JsonGetter("trace")
    public String getTrace() {
        return trace;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, trace);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorDto errorDto = (ErrorDto) o;
        return code == errorDto.code && Objects.equals(message, errorDto.message)
                && Objects.equals(trace, errorDto.trace);
    }
}

