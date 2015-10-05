/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.impl;

import fr.esecure.banking.modele.client.entities.AccuseReception;
import fr.esecure.banking.service.IEsecureDaoAccuseReception;
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
public class IEsecureDaoAccuseReceptionImpl implements IEsecureDaoAccuseReception{

   
@PersistenceContext
 EntityManager em;
    public <S extends AccuseReception> S save(S s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.merge(s);
    }

    public <S extends AccuseReception> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AccuseReception findOne(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(AccuseReception.class, id);
    }

    public boolean exists(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  return (em.find(AccuseReception.class, id).getIdAccuseReception()>0);
    }

    public Iterable<AccuseReception> findAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createQuery("Select * from transaction").getResultList();
    }

    public Iterable<AccuseReception> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createNamedQuery("Select * from transaction").getMaxResults();
    }

    public void delete(Long id) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  em.remove(em.find(AccuseReception.class, id));
    }

    public void delete(AccuseReception t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        em.remove(t);
    }

    public void delete(Iterable<? extends AccuseReception> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
