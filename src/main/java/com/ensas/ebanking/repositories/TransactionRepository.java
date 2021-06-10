package com.ensas.ebanking.repositories;

import com.ensas.ebanking.models.Transaction;
import com.ensas.ebanking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
