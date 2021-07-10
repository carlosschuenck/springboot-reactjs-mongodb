package com.contact.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ApiExceptionResponse {
    private List<String> errors;
}
