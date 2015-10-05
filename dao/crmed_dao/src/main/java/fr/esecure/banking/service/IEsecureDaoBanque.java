package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.Banque;
import org.springframework.data.repository.CrudRepository;

public interface IEsecureDaoBanque extends CrudRepository<Banque, Long>{
    public Banque findCurrentBanque();
}
