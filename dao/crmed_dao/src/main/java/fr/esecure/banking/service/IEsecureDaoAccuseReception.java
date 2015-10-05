package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.AccuseReception;
import org.springframework.data.repository.CrudRepository;

public interface IEsecureDaoAccuseReception extends CrudRepository<AccuseReception, Long>{

}
