package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Role;
import com.ensas.ebanking.models.Transaction;
import com.ensas.ebanking.services.TransactionService;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.utils.ModelValidator;
import com.ensas.ebanking.vo.TransactionVo;
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
@RequestMapping("transaction")
@CrossOrigin(origins = {"*"})
public class TransactionController {

    TransactionService transactionService;

    @Autowired
    @Qualifier("transactionConverter")
    private AbstractConverter<Transaction, TransactionVo> transactionConverter;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    // @PreAuthorize("hasRole('HELLO')")
    List<TransactionVo> getAll() {
        List<TransactionVo> transactions = transactionConverter.toVo(transactionService.getAllTransactions());
        return transactions;
    }

    @PostMapping
    TransactionVo create(@RequestBody @Valid TransactionVo transactionvo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        Transaction transaction = transactionConverter.toItem(transactionvo);
        return transactionConverter.toVo(transactionService.saveTransaction(transaction));
    }

    @GetMapping("/{id}")
    TransactionVo getOne(@PathVariable Long id) {
        return transactionConverter.toVo(transactionService.getOneTransaction(id));
    }

    @PutMapping("/{id}")
    int update(@RequestBody TransactionVo transactionVo, @PathVariable Long id) {
        Transaction transaction = transactionConverter.toItem(transactionVo);
        return transactionService.updateTransaction(transaction,id) != null ? 1:0;
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        // some verification here
        transactionService.deleteTransaction(id);
    }

}
