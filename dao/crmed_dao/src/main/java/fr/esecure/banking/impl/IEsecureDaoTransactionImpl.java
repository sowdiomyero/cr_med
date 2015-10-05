/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.impl;

import fr.esecure.banking.modele.client.entities.Transaction;
import fr.esecure.banking.service.IEsecureDaoTransaction;
import java.util.Date;
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
public class IEsecureDaoTransactionImpl implements IEsecureDaoTransaction{
 @PersistenceContext
 EntityManager em;
    public <S extends Transaction> S save(S s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.merge(s);
    }

    public <S extends Transaction> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Transaction findOne(Long id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.find(Transaction.class, id);
    }

    public boolean exists(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  return (em.find(Transaction.class, id).getIdTransaction()>0);
    }

    public Iterable<Transaction> findAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createQuery("Select * from transaction").getResultList();
    }

    public Iterable<Transaction> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em.createNamedQuery("Select * from transaction").getMaxResults();
    }

    public void delete(Long id) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  em.remove(em.find(Transaction.class, id));
    }

    public void delete(Transaction t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        em.remove(t);
    }

    public void delete(Iterable<? extends Transaction> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public List<Transaction> findAllTransactions() {
         //Méthode qui retournent la liste des transactions
    return (List<Transaction>) em.createNamedQuery(Transaction.FIND_ALL_TRANSACTION).getResultList();
     }

    public List<Transaction> findAllTransactionNotValidated() {
          
  return (List<Transaction>) em.createNamedQuery(Transaction.FIND_TRANSACTION_BY_STATE)
          .setParameter("state",Transaction.TRANSACTION_STATES.REJECTED ).getResultList();    }

    public Transaction findTransactionById(Long id) {
     Transaction transaction =   em.createNamedQuery(Transaction.FIND_TRANSACTION_BY_ID,Transaction.class)
          .setParameter("id",id ).getSingleResult();
 return transaction;
     
    }

    public List<Transaction> findTransactionsBetweenDate(Date startDate, Date endDate) {
      return (List<Transaction>) em.createNamedQuery(Transaction.FIND_TRANSACTIONS_BETWEEN_DATE)
          .setParameter("startDate",startDate )
          .setParameter("endDate",endDate ) 
          .getResultList();
    }

    public List<Transaction> findTransactionsToday(Date date) { 
         return (List<Transaction>) em.createNamedQuery(Transaction.FIND_TRANSACTIONS_TODAY)
          .setParameter("dateToday",date).getResultList();
    }
}
