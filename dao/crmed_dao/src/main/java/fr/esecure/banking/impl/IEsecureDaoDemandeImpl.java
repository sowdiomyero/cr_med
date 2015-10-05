/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esecure.banking.impl;

import fr.esecure.banking.modele.client.entities.Demande;
import fr.esecure.banking.service.IEsecureDaoDemande;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SNIANG
 */
@Transactional
@Repository
public class IEsecureDaoDemandeImpl implements IEsecureDaoDemande {

    @PersistenceContext
    EntityManager em;

    public <S extends Demande> S save(S s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.merge(s);
    }

    public <S extends Demande> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Demande findOne(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Demande.class, id);
    }

    public boolean exists(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return (em.find(Demande.class, id).getIdDemande() > 0);
    }

    public Iterable<Demande> findAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createQuery("Select * from transaction").getResultList();
    }

    public Iterable<Demande> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createNamedQuery("Select * from transaction").getMaxResults();
    }

    public void delete(Long id) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        em.remove(em.find(Demande.class, id));
    }

    public void delete(Demande t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        em.remove(t);
    }

    public void delete(Iterable<? extends Demande> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Demande findDemandeById(Long idDemande) {

        Demande demande = em.createNamedQuery(Demande.FIND_DEMANDE_BY_ID, Demande.class).setParameter("idDemande", idDemande).getSingleResult();
        return demande;

    }
    
    //mise à jour de le demande
    public Long updateDemande(Demande demande) {
        em.merge(demande);
        return demande.getIdDemande();
    }

}
