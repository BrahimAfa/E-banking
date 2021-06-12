package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.security.services.UserDetailsImpl;
import com.ensas.ebanking.services.AccountService;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.utils.ModelValidator;
import com.ensas.ebanking.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/api/account")
@CrossOrigin(origins = {"*"})
public class AccountController {

    AccountService accountService;

    @Autowired
    @Qualifier("accountConverter")
    private AbstractConverter<Account, AccountVo> accountConverter;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @PreAuthorize("hasRole('HELLO')")
    List<AccountVo> getAll() {
        List<AccountVo> accounts = accountConverter.toVo(accountService.getAllAccounts());
        return accounts;
    }

    @PostMapping
    AccountVo create(@RequestBody @Valid AccountVo accountvo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        Account account = accountConverter.toItem(accountvo);
        Set<Role> accountRole = new HashSet<>();
        accountRole.add(new Role(1,"CLIENT",true));
        return accountConverter.toVo(accountService.saveAccount(account));
    }

    @GetMapping("/{id}")
    AccountVo getOne(@PathVariable Long id) {
        return accountConverter.toVo(accountService.getOneAccount(id));
    }

    @PutMapping("/{id}")
    int update(@RequestBody AccountVo accountVo, @PathVariable Long id) {
        Account account = accountConverter.toItem(accountVo);
        return accountService.updateAccount(account,id) != null ? 1:0;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CLIENT')")
    List<AccountVo> me(Authentication auth) {
        UserDetailsImpl j = (UserDetailsImpl)auth.getPrincipal();
        return accountConverter.toVo(accountService.getByClientId(j.getUser().getUserId()));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        // some verification here
        accountService.deleteAccount(id);
    }

}
