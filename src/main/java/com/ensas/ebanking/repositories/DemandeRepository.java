package com.ensas.ebanking.repositories;

import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
}
