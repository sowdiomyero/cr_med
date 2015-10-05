/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.impl;

import fr.esecure.banking.modele.client.entities.Banque;
import fr.esecure.banking.service.IEsecureDaoBanque;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author SNIANG
 */
@Transactional
@Repository
public class IEsecureDaoBanqueImpl implements IEsecureDaoBanque{

  @PersistenceContext
 EntityManager em;
    public <S extends Banque> S save(S s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.merge(s);
    }

    public <S extends Banque> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Banque findOne(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Banque.class, id);
    }

    public boolean exists(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return (em.find(Banque.class, id).getIdBanque()>0);
    }

    public Iterable<Banque> findAll() {

        return  em.createNamedQuery(Banque.FIND_BANQUE_USER).getResultList();
    }
    public Banque findCurrentBanque() {

        List<Banque> banques = (List<Banque>) em.createNamedQuery(Banque.FIND_BANQUE_USER).getResultList();
        if(banques != null && banques.size()>0){
            return banques.get(0);
        }
        return null;
    }

    public Iterable<Banque> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        return em.createNamedQuery(Banque.FIND_BANQUE_USER).getMaxResults();
    }

    public void delete(Long id) {
        em.remove(em.find(Banque.class, id));
    }

    public void delete(Banque t) {
        em.remove(t);
    }

    public void delete(Iterable<? extends Banque> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
