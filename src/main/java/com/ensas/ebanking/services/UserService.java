package com.ensas.ebanking.services;

import com.ensas.ebanking.exceptions.ModelNotFoundException;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository clientRepository;

    public UserService(UserRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<User> getAllClients(){
        return clientRepository.findAll();
    }

    public Optional<User> getByEmail(String email){
        return clientRepository.findByEmail(email);
    }
    public Optional<User> getByUsername(String username){
        return clientRepository.findByUsername(username);
    }

    public Boolean existsByEmail(String email){
        return clientRepository.existsByEmail(email);
    }

    public User getOneClient(long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Client" ,id));
    }

    public User saveClient(User user){
        return clientRepository.save(user);
    }

    public User updateClient(User user, long id){
        return clientRepository.findById(id)
                .map(c -> {
                    if(user.getEmail() != null) c.setEmail(user.getEmail());
                    if(user.getLastname() != null) c.setFirstname(user.getLastname());
                    if(user.getFirstname() != null) c.setLastname(user.getFirstname());
                    if(user.getPassword() != null) c.setPassword(user.getPassword());
                    if(user.getRoles() != null) c.setRoles(user.getRoles());
                    return clientRepository.save(c);
                })
                .orElse(null);
    }

    public void deleteClient(long id){
        clientRepository.deleteById(id);
    }
}
