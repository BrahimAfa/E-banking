package com.ensas.ebanking.repositories;

import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNum(String num);
    List<Account> findByClientUserId(long userId);
}
