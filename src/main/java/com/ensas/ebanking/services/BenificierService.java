package com.ensas.ebanking.services;

import com.ensas.ebanking.exceptions.ModelNotFoundException;
import com.ensas.ebanking.models.Benificier;
import com.ensas.ebanking.repositories.BenificierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenificierService {
    BenificierRepository benificierRepository;

    public BenificierService(BenificierRepository benificierRepository) {
        this.benificierRepository = benificierRepository;
    }

    public List<Benificier> getAllBenificiers(){
        return benificierRepository.findAll();
    }


    public Benificier getOneBenificier(long id){
        return benificierRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Benificier" ,id));
    }

    public List<Benificier> getByClientID(long id){
        return benificierRepository.findByClientUserId(id)
                .orElseThrow(() -> new ModelNotFoundException("Benificier" ,id));
    }

    public Benificier getByClinetId(long id){
        return benificierRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Benificier" ,id));
    }

    public Benificier saveBenificier(Benificier benificier){
        return benificierRepository.save(benificier);
    }

    public Benificier updateBenificier(Benificier benificier, long id){
        return benificierRepository.findById(id)
                .map(c -> {

                    return benificierRepository.save(c);
                })
                .orElse(null);
    }
    public void deleteBenificier(long id){
        benificierRepository.deleteById(id);
    }


}
