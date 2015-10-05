package fr.esecure.banking.impl;

import fr.esecure.banking.modele.client.entities.CompteRendu;
import fr.esecure.banking.service.IEsecureDaoCompteRendu;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Transactional
@Repository
public class IEsecureDaoCompteRenduImpl implements Serializable,IEsecureDaoCompteRendu{
   
@PersistenceContext
 EntityManager em;
    public <S extends CompteRendu> S save(S s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.merge(s);
    }

    public <S extends CompteRendu> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CompteRendu findOne(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(CompteRendu.class, id);
    }

    public boolean exists(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  return (em.find(CompteRendu.class, id).getIdCompteRendu()>0);
    }

    public Iterable<CompteRendu> findAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createQuery("Select * from transaction").getResultList();
    }

    public Iterable<CompteRendu> findAll(Iterable<Long> itrbl) {
    return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createNamedQuery("Select * from transaction").getMaxResults();
    }

    public void delete(Long id) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  em.remove(em.find(CompteRendu.class, id));
        System.out.println("suppression réussie");
    }

    public void delete(CompteRendu t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        em.remove(t);
    }

    public void delete(Iterable<? extends CompteRendu> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
