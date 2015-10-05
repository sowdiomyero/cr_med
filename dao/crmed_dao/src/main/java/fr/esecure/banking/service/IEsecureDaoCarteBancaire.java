package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.CarteBancaire;
import org.springframework.data.repository.CrudRepository;


public interface IEsecureDaoCarteBancaire extends CrudRepository<CarteBancaire, Long>{

}
