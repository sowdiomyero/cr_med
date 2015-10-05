package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface IEsecureDaoClient extends CrudRepository<Client, Long>{

}
