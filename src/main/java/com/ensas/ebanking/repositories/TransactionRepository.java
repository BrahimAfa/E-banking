package com.ensas.ebanking.repositories;

import com.ensas.ebanking.models.Transaction;
import com.ensas.ebanking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select sum(t.amount) from Transaction t \n" +
            "inner join Account a on a.id = t.account.id\n" +
            "inner join User u on u.userId= a.client.userId\n" +
            "where u.userId=:id and t.transactionType=:type")
    Optional<Double> sumOfCreditByClient(@Param("id") long id,@Param("type") String type);
}
