package com.transferenciasimplificada.repositories;

import com.transferenciasimplificada.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
