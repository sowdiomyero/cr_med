package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.Demande;
import org.springframework.data.repository.CrudRepository;

public interface IEsecureDaoDemande extends CrudRepository<Demande, Long>{
public Demande findDemandeById(Long idDemande);
public Long updateDemande(Demande demande );
}
