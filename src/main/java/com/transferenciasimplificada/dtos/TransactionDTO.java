package com.transferenciasimplificada.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public final class TransactionDTO {
    private final BigDecimal value;
    private final Long senderId;
    private final Long receiverId;

        public BigDecimal value() {
        return value;
    }

    public Long senderId() {
        return senderId;
    }

    public Long receiverId() {
        return receiverId;
    }
}
