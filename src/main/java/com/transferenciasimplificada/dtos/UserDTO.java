package com.transferenciasimplificada.dtos;

import com.transferenciasimplificada.domain.user.UserType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public final class UserDTO {

    private final String firstName;
    private final String lastName;
    private final String document;
    private final BigDecimal balance;
    private final String email;
    private final String password;
    private final UserType userType;

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String document() {
        return document;
    }

    public BigDecimal balance() {
        return balance;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public UserType userType() {
        return userType;
    }
}
