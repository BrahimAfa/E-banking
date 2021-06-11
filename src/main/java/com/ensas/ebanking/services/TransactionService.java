package com.ensas.ebanking.services;

import com.ensas.ebanking.exceptions.ModelNotFoundException;
import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.Benificier;
import com.ensas.ebanking.models.Transaction;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.repositories.AccountRepository;
import com.ensas.ebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;
    @Autowired
    AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction getOneTransaction(long id){
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Transaction" ,id));
    }
    @Transactional
    public Transaction saveTransaction(Transaction transaction){
        transaction.setTransactionType("CREDIT");
        User client =  accountService.getOneAccount(transaction.getAccount().getId()).getClient();
        Benificier benificier = transaction.getBenificier();
        System.out.println("befor Virment");
        System.out.println(transaction);

        System.out.println(benificier);
        if(transaction.getType().equals("VIREMENT")){
            System.out.println("in Virment");

            if(benificier.getAccountNum().startsWith("836577658273")){
                System.out.println("it start with samati DEC");

                Account BenificiereAccount = accountService.getByAccountNum(benificier.getAccountNum());
                System.out.println("befor benif");

                if(BenificiereAccount!=null){
                    System.out.println("in our benific");

                    Transaction tr = new Transaction();
                    tr.setTransactionType("DEBIT");
                    tr.setType("VIRMENT");
                    tr.setAccount(BenificiereAccount);
                    tr.setAmount(transaction.getAmount());
                    tr.setName(client.getFirstname()+" "+client.getLastname());
                    System.out.println("after transaction set");
                    transactionRepository.save(tr);
                    System.out.println("after added");

                    accountService.updateBlanace(BenificiereAccount.getId(),tr.getAmount(),true);
                    System.out.println("account addedd balance++++");

                }else {
                    System.out.println("this account is somehow not in our database!");
                }
            }
        }
        Transaction savedTransaction = transactionRepository.save(transaction);
        accountService.updateBlanace(transaction.getAccount().getId(),transaction.getAmount(),false);
        return savedTransaction;
    }

    public Transaction updateTransaction(Transaction transaction, long id){
        return transactionRepository.findById(id)
                .map(c -> {
                    if(transaction.getAmount() !=0 ) c.setAmount(transaction.getAmount());
                    if(transaction.getName() !=null ) c.setName(transaction.getName());
                    if(transaction.getBenificier() !=null ) c.setBenificier(transaction.getBenificier());
                    if(transaction.getDescription() != null ) c.setDescription(transaction.getDescription());
                    return transactionRepository.save(c);
                })
                .orElse(null);
    }

    public Double getCreditsByClient(long id,String type){
        return transactionRepository.sumOfCreditByClient(id,type).orElse(0d);
    }




    public void deleteTransaction(long id){
        transactionRepository.deleteById(id);
    }
}
