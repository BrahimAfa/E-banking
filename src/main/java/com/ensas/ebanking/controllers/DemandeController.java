package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.Demande;
import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.models.Demande;
import com.ensas.ebanking.security.services.UserDetailsImpl;
import com.ensas.ebanking.services.TransactionService;
import com.ensas.ebanking.services.DemandeService;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.utils.ModelValidator;
import com.ensas.ebanking.vo.AccountVo;
import com.ensas.ebanking.vo.ClientDashboard;
import com.ensas.ebanking.vo.DemandeVo;
import com.ensas.ebanking.vo.DemandeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("demande")
@CrossOrigin(origins = {"*"})
public class DemandeController {
    @Autowired
    DemandeService demandeService;

    @Autowired
    @Qualifier("demandeConverter")
    private AbstractConverter<Demande, DemandeVo> demandeConverter;

    @Autowired
    @Qualifier("accountConverter")
    private AbstractConverter<Account, AccountVo> accountConverter;

    public DemandeController() {
    }

    @GetMapping
    @PreAuthorize("hasRole('CLIENT')")
    List<DemandeVo> getAll(Authentication auth) {
        List<DemandeVo> demandes = demandeConverter.toVo(demandeService.getAllDemandes());
        return demandes;
    }

    @PostMapping
    DemandeVo create(@RequestBody @Valid DemandeVo demandevo, BindingResult bindingResult,Authentication auth) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        Demande demande = demandeConverter.toItem(demandevo);
        UserDetailsImpl j= (UserDetailsImpl)auth.getPrincipal();
        demande.setClient(j.getUser());
        return demandeConverter.toVo(demandeService.saveDemande(demande));
    }

    @GetMapping("/{id}")
    DemandeVo getOne(@PathVariable Long id) {
        return demandeConverter.toVo(demandeService.getOneDemande(id));
    }

    @PutMapping("/{id}")
    int update(@RequestBody DemandeVo demandeVo, @PathVariable Long id) {
        Demande demande = demandeConverter.toItem(demandeVo);
        return demandeService.updateDemande(demande,id) != null ? 1:0;
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        // some verification here
        demandeService.deleteDemande(id);
    }



}
