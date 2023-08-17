package com.transferenciasimplificada.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ExceptionDTO {
    private final String statusCode;
    private final String message;

    public String statusCode() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
