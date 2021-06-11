package com.ensas.ebanking.services;

import com.ensas.ebanking.exceptions.ModelNotFoundException;
import com.ensas.ebanking.models.Demande;
import com.ensas.ebanking.models.User;
import com.ensas.ebanking.repositories.DemandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService {
    DemandeRepository demandeRepository;

    public DemandeService(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }

    public List<Demande> getAllDemandes(){
        return demandeRepository.findAll();
    }


    public Demande getOneDemande(long id){
        return demandeRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Demande" ,id));
    }


    public Demande saveDemande(Demande demande){
        return demandeRepository.save(demande);
    }

    public Demande updateDemande(Demande demande, long id){
        return demandeRepository.findById(id)
                .map(c -> {
                    if(demande.getType() != null) c.setType(demande.getType());
                    if(demande.getStatus()!=null) c.setStatus(demande.getStatus());
                    if(demande.getClient() != null) c.setClient(demande.getClient());
                    if(demande.getAccount() != null) c.setAccount(demande.getAccount());
                    if(demande.getAgnceToTransfer() != null) c.setAgnceToTransfer(demande.getAgnceToTransfer());
                    if(demande.getVille() != null) c.setVille(demande.getVille());
                    return demandeRepository.save(c);
                })
                .orElse(null);
    }

    public void deleteDemande(long id){
        demandeRepository.deleteById(id);
    }
}
