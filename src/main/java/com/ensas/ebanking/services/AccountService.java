package com.ensas.ebanking.services;

import com.ensas.ebanking.exceptions.ModelNotFoundException;
import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }


    public Account getOneAccount(long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Account" ,id));
    }



    public List<Account> getByClientId(long id){
        List<Account> accounts = accountRepository.findByClientUserId(id);
        if(accounts==null || accounts.size()<=0) return null;
        return  accounts;

    }


    public Account getByClinetId(long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Account" ,id));
    }


    public Account getByAccountNum(String num){
        return accountRepository.findByAccountNum(num)
                .orElseThrow(() -> new ModelNotFoundException("Account" ,num));
    }

    public Account updateBlanace(long id,double amount,boolean isPlus) {
        return accountRepository.findById(id)
                .map(c -> {
                    if(isPlus){
                        c.setBalance(c.getBalance()+amount);
                    }else{
                        c.setBalance(c.getBalance()-amount);
                    }
                    if(c.getBalance()<=0) return null;
                    return accountRepository.save(c);
                }).orElse(null);
    }

    public Account saveAccount(Account account){
        account.setAccountNum("836577658273"+account.getAccountNum());
        return accountRepository.save(account);
    }

    public Account updateAccount(Account account, long id){
        return accountRepository.findById(id)
                .map(c -> {
                    if(account.getAccountNum() != null) c.setAccountNum(account.getAccountNum());
                    if(account.getBalance()!=0) c.setBalance(account.getBalance());
                    if(account.getCurrency() != null) c.setCurrency(account.getCurrency());
                    if(account.getClient() != null) c.setClient(account.getClient());
                    if(account.getName() != null) c.setName(account.getName());
                    if(account.getType() != null) c.setType(account.getType());

                    return accountRepository.save(c);
                })
                .orElse(null);
    }

    public void deleteAccount(long id){
        accountRepository.deleteById(id);
    }
}
