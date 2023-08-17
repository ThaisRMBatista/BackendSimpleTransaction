package com.transferenciasimplificada.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExceptionDTO {
    private final String message;
    private final String statusCode;

    public ExceptionDTO(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String message() {
        return message;
    }

    public String statusCode() {
        return statusCode;
    }
}
