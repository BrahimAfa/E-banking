package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.models.Benificier;
import com.ensas.ebanking.security.services.UserDetailsImpl;
import com.ensas.ebanking.services.TransactionService;
import com.ensas.ebanking.services.BenificierService;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.utils.ModelValidator;
import com.ensas.ebanking.vo.AccountVo;
import com.ensas.ebanking.vo.ClientDashboard;
import com.ensas.ebanking.vo.BenificierVo;
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
@RequestMapping("benificier")
@CrossOrigin(origins = {"*"})
public class BenificierController {
    @Autowired
    BenificierService userService;

    @Autowired
    @Qualifier("benificierConverter")
    private AbstractConverter<Benificier, BenificierVo> benificierConverter;

    @Autowired
    @Qualifier("accountConverter")
    private AbstractConverter<Account, AccountVo> accountConverter;

    public BenificierController() {
    }

    @GetMapping
    @PreAuthorize("hasRole('CLIENT')")
    List<BenificierVo> getAll(Authentication auth) {
        List<BenificierVo> benificiers = benificierConverter.toVo(userService.getAllBenificiers());
        return benificiers;
    }

    @PostMapping
    BenificierVo create(@RequestBody @Valid BenificierVo benificier, BindingResult bindingResult,Authentication auth) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        Benificier ben = benificierConverter.toItem(benificier);
        UserDetailsImpl j = (UserDetailsImpl)auth.getPrincipal();
        ben.setClient(j.getUser());
        return benificierConverter.toVo(userService.saveBenificier(ben));
    }

    @GetMapping("/{id}")
    BenificierVo getOne(@PathVariable Long id) {
        return benificierConverter.toVo(userService.getOneBenificier(id));
    }

    @PutMapping("/{id}")
    int update(@RequestBody BenificierVo benificierVo, @PathVariable Long id) {
        Benificier ben = benificierConverter.toItem(benificierVo);
        return userService.updateBenificier(ben,id) != null ? 1:0;
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        // some verification here
        userService.deleteBenificier(id);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication auth) {
        UserDetailsImpl j = (UserDetailsImpl)auth.getPrincipal();
        return ResponseEntity.ok(benificierConverter.toVo(userService.getByClientID(j.getUser().getUserId())));
    }


}
