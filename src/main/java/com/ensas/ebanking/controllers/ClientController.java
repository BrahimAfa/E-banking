package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.Agency;
import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.security.services.UserDetailsImpl;
import com.ensas.ebanking.services.TransactionService;
import com.ensas.ebanking.services.UserService;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.utils.ModelValidator;
import com.ensas.ebanking.vo.AccountVo;
import com.ensas.ebanking.vo.ClientDashboard;
import com.ensas.ebanking.vo.ClientVo;
import com.ensas.ebanking.vo.converters.ClientConverter;
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
@RequestMapping("client")
@CrossOrigin(origins = {"*"})
public class ClientController {
    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    @Qualifier("clientConverter")
    private AbstractConverter<User, ClientVo> clientConverter;

    @Autowired
    @Qualifier("accountConverter")
    private AbstractConverter<Account, AccountVo> accountConverter;

    public ClientController() {
    }

    @GetMapping
    @PreAuthorize("hasRole('CLIENT')")
    List<ClientVo> getAll(Authentication auth) {
        List<ClientVo> clients = clientConverter.toVo(userService.getAllClients());
        return clients;
    }

    @PostMapping
    ClientVo create(@RequestBody @Valid ClientVo client, BindingResult bindingResult,Authentication auth) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        UserDetailsImpl userLog = (UserDetailsImpl)auth.getPrincipal();
        User user = clientConverter.toItem(client);
        user.setAgency(userLog.getUser().getAgency());
        user.setResponsableAgent(userLog.getUser());
        System.out.println("hellloooo");

    //    System.out.println(user);
        Set<Role> clientRole = new HashSet<>();
        clientRole.add(new Role(1,"ROLE_CLIENT",true));
        user.setRoles(clientRole);
        user.setPassword(encoder.encode(client.getPassword()));
        return clientConverter.toVo(userService.saveClient(user));
    }

    @GetMapping("/{id}")
    ClientVo getOne(@PathVariable Long id) {
        return clientConverter.toVo(userService.getOneClient(id));
    }

    @PutMapping("/{id}")
    int update(@RequestBody ClientVo clientVo, @PathVariable Long id) {
        User user = clientConverter.toItem(clientVo);
        if(clientVo.getPassword()!=null) user.setPassword(encoder.encode(clientVo.getPassword()));
        return userService.updateClient(user,id) != null ? 1:0;
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        // some verification here
        userService.deleteClient(id);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication auth) {
        UserDetailsImpl j = (UserDetailsImpl)auth.getPrincipal();
        return ResponseEntity.ok(clientConverter.toVo(j.getUser()));
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('CLIENT')")
    ClientDashboard dashboard(Authentication auth) {
        UserDetailsImpl user = (UserDetailsImpl)auth.getPrincipal();
        transactionService.getCreditsByClient(user.getUser().getUserId(),"CREDIT");
        Double debit = transactionService.getCreditsByClient(user.getUser().getUserId(),"DEBIT");
        Double credit = transactionService.getCreditsByClient(user.getUser().getUserId(),"CREDIT");
        ClientDashboard dash = new ClientDashboard();
        dash.setCredit(credit);
        dash.setDebit(debit);
        dash.setFaild(0d);
        dash.setBlocked(0d);
        dash.setAccounts(accountConverter.toVo(user.getUser().getAccounts()));
        return dash;
    }

}
