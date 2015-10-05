package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.Adresse;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IEsecureDaoAdresse extends CrudRepository<Adresse, Long>{
    public List<Adresse> getAdresseByClientId(Long idClient);
}
