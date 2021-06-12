package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.security.services.UserDetailsImpl;
import com.ensas.ebanking.services.TransactionService;
import com.ensas.ebanking.services.UserService;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.utils.ModelValidator;
import com.ensas.ebanking.vo.AccountVo;
import com.ensas.ebanking.vo.ClientDashboard;
import com.ensas.ebanking.vo.AgentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = {"*"})
public class AgentController {
    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    @Qualifier("agentConverter")
    private AbstractConverter<User, AgentVo> agentConverter;

    @Autowired
    @Qualifier("accountConverter")
    private AbstractConverter<Account, AccountVo> accountConverter;

    public AgentController() {
    }

    @GetMapping
    @PreAuthorize("hasRole('AGENT')")
    List<AgentVo> getAll(Authentication auth) {
        List<AgentVo> agents = agentConverter.toVo(userService.getAllClients());
        return agents;
    }

    @PostMapping
    AgentVo create(@RequestBody @Valid AgentVo agentvo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        User user = agentConverter.toItem(agentvo);
       //  user.setResponsableAgent(new User());
        System.out.println("hellloooo");

        System.out.println(user);
        Set<Role> agentRole = new HashSet<>();
        agentRole.add(new Role(1,"ROLE_AGENT",true));
        user.setRoles(agentRole);
        user.setPassword(encoder.encode(agentvo.getPassword()));
        return agentConverter.toVo(userService.saveClient(user));
    }

    @GetMapping("/{id}")
    AgentVo getOne(@PathVariable Long id) {
        return agentConverter.toVo(userService.getOneClient(id));
    }

    @PutMapping("/{id}")
    int update(@RequestBody AgentVo agentVo, @PathVariable Long id) {
        User user = agentConverter.toItem(agentVo);
        if(agentVo.getPassword()!=null) user.setPassword(encoder.encode(agentVo.getPassword()));
        return userService.updateClient(user,id) != null ? 1:0;
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        // some verification
        userService.deleteClient(id);
    }

    @GetMapping("/clients")
    public ResponseEntity<?> me(Authentication auth) {
        UserDetailsImpl j = (UserDetailsImpl)auth.getPrincipal();
        List<User> users = userService.getByAgencyId(j.getUser().getAgency().getId());
        List<User> clients = new ArrayList<>();
        for (User user : users){
            if (user.getRoles().stream().allMatch(r->r.getName().equals("ROLE_CLIENT"))){
                clients.add(user);
            }
        }
        return ResponseEntity.ok(agentConverter.toVo(clients));
    }

    @GetMapping("/dashboard")

    @PreAuthorize("hasRole('AGENT')")
    String dashboard(Authentication auth) {
        return "NOT IMPLEMENTED YET";
    }

}
