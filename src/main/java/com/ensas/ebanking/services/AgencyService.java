package com.ensas.ebanking.services;

import com.ensas.ebanking.exceptions.ModelNotFoundException;
import com.ensas.ebanking.models.Agency;
import com.ensas.ebanking.repositories.AgencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {
    AgencyRepository agencyRepository;

    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public List<Agency> getAllAgencys(){
        return agencyRepository.findAll();
    }


    public Agency getOneAgency(long id){
        return agencyRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Agency" ,id));
    }


    public Agency saveAgency(Agency agency){
        return agencyRepository.save(agency);
    }

    public Agency updateAgency(Agency agency, long id){
        return agencyRepository.findById(id)
                .map(c -> {
                    // updatee
                    return agencyRepository.save(c);
                })
                .orElse(null);
    }

    public void deleteAgency(long id){
        agencyRepository.deleteById(id);
    }
}
