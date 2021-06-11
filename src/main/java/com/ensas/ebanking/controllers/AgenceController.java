package com.ensas.ebanking.controllers;

import com.ensas.ebanking.exceptions.ClientValidationException;
import com.ensas.ebanking.models.Account;
import com.ensas.ebanking.models.Agency;
import com.ensas.ebanking.services.AgencyService;
import com.ensas.ebanking.utils.AbstractConverter;
import com.ensas.ebanking.utils.ModelValidator;
import com.ensas.ebanking.vo.AccountVo;
import com.ensas.ebanking.vo.AgencyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("agency")
@CrossOrigin(origins = {"*"})
public class AgenceController {
    @Autowired
    AgencyService agencyService;

    @Autowired
    @Qualifier("agencyConverter")
    private AbstractConverter<Agency, AgencyVo> agencyConverter;

    @Autowired
    @Qualifier("accountConverter")
    private AbstractConverter<Account, AccountVo> accountConverter;

    public AgenceController() {
    }

    @GetMapping
    @PreAuthorize("hasRole('CLIENT')")
    List<AgencyVo> getAll(Authentication auth) {
        List<AgencyVo> agencys = agencyConverter.toVo(agencyService.getAllAgencys());
        return agencys;
    }

    @PostMapping
    AgencyVo create(@RequestBody @Valid AgencyVo agencyvo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ClientValidationException(ModelValidator.getErrorsFromBindingResult(bindingResult));
        }
        Agency agency = agencyConverter.toItem(agencyvo);
        return agencyConverter.toVo(agencyService.saveAgency(agency));
    }

    @GetMapping("/{id}")
    AgencyVo getOne(@PathVariable Long id) {
        return agencyConverter.toVo(agencyService.getOneAgency(id));
    }

    @PutMapping("/{id}")
    int update(@RequestBody AgencyVo agencyVo, @PathVariable Long id) {
        Agency agency = agencyConverter.toItem(agencyVo);
        return agencyService.updateAgency(agency,id) != null ? 1:0;
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        // some verification here
        agencyService.deleteAgency(id);
    }

}
