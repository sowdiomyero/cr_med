package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.CompteRendu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEsecureDaoCompteRendu extends CrudRepository<CompteRendu, Long>{


}
