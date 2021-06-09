package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.services.UserService;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.utils.ModelValidator;
import com.ensas.ebanking.vo.ClientVo;
import com.ensas.ebanking.vo.converters.ClientConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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

    UserService userService;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    @Qualifier("clientConverter")
    private AbstractConverter<User, ClientVo> clientConverter;

    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('HELLO')")
    List<ClientVo> getAll() {
        List<ClientVo> clients = clientConverter.toVo(userService.getAllClients());

        return clients;
    }

    @PostMapping
    ClientVo create(@RequestBody @Valid ClientVo client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        User user = clientConverter.toItem(client);
        Set<Role> clientRole = new HashSet<>();
        clientRole.add(new Role(1,"CLIENT",true));
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

}
