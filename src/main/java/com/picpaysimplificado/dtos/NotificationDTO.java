package com.picpaysimplificado.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public final class NotificationDTO {
    private final String email;
    private final String message;

    public String email() {
        return email;
    }

    public String message() {
        return message;
    }
}
