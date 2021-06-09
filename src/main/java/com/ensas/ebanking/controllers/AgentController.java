package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.services.UserService;
import com.ensas.ebanking.utils.ModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("agent")
@CrossOrigin(origins = {"*"})
public class AgentController {

    UserService userService;
    @Autowired
    PasswordEncoder encoder;
    public AgentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<User> all() {
        return userService.getAllClients();
    }

    @PostMapping
    User newClient(@RequestBody @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        Set<Role> clientRole = new HashSet<>();
        clientRole.add(new Role(1,"CLIENT",true));
        user.setRoles(clientRole);
        return userService.saveClient(user);
    }

    @GetMapping("/{id}")
    User one(@PathVariable Long id) {
        return userService.getOneClient(id);
    }

    @PutMapping("/{id}")
    User replaceClient(@RequestBody User user, @PathVariable Long id) {
        user.setPassword(encoder.encode(user.getPassword()));

        return userService.updateClient(user,id);
    }

    @DeleteMapping("/{id}")
    void deleteClient(@PathVariable Long id) {
        userService.deleteClient(id);
    }

}
