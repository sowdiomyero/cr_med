/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.impl;

import fr.esecure.banking.modele.client.entities.User;
import fr.esecure.banking.service.IEsecureDaoUsers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author sniang
 */
@Transactional
@Repository
public class IEsecureDaoUserImpl  implements IEsecureDaoUsers{

    @PersistenceContext
 EntityManager em;

    public <S extends User> S save(S s) {
       return em.merge(s);
    }

    public <S extends User> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User findOne(Long id) {
       return em.find(User.class, id);
    }
    public boolean exists(Long id) {
      return (em.find(User.class, id).getIdUser()>0);
    }

    public Iterable<User> findAll() {
        return em.createQuery("Select * from users").getResultList();
    }

    public Iterable<User> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
         return em.createNamedQuery("Select * from users").getMaxResults();
    }

    public void delete(Long id) {
        em.remove(em.find(User.class, id));
    }

    public void delete(User t) {
        em.remove(t);
    }

    public void delete(Iterable<? extends User> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<User> findAllUsers() {
        return (List<User>) em.createNamedQuery(User.FIND_ALL_USERS).getResultList();
    }

    @Override
    public boolean isUserWithEmailExist(String emailAdresse) {
        List<User> users=null;
        try{
            users=(List<User>) em.createQuery("select u from User u where u.userMail=:userMail").setParameter("userMail", emailAdresse)
                    .getResultList();
        }catch(Exception ex){
         System.out.println("Exception survenue dans la methode isUserWithEmailExist  ["+ex.getMessage()+"]");
            return false;
        }
        if (users !=null && users.size()>0)
            return true;
        else
            return false;
    }
//Methode qui retourne un utilisateur à partir de son id
    public User findUserById(Long idUser) {
      List<User> users =    em.createNamedQuery(User.FIND_USER_BY_ID,User.class)
          .setParameter("idUser",idUser ).getResultList();
      User user= users.get(0);
        return user;
      }
//Methode qui retourne un utilisateur à partir de son adresse email
    public User findUserByEmail(String userMail) {
       List<User> users =   em.createNamedQuery(User.FIND_USER_BY_EMAIL,User.class)
          .setParameter("userMail",userMail ).getResultList();
       User user= users.get(0);
        return user; 
    }
//Methode de mise à jour d'un utilisateur
    public void updateUser(User editUser) {
       User user =em.merge(editUser);
        System.out.println(user.getCompte().getPassword());
        
    }

    public User findUserByIdCompte(Long idCompte) {
    List<User> users =   em.createNamedQuery(User.FIND_USER_BY_ID_COMPTE,User.class)
          .setParameter("idCompte",idCompte ).getResultList();
       User user= users.get(0);
        return user; 
    }

    public List<User> findAllUsersWithoutUserConnected(String login) {   
         List<User> users=null;
        try{
            users=(List<User>) em.createQuery("select u from User u where u.compte.login <>'"+login+"'")
                    .getResultList();
        }catch(Exception ex){
            return null;
        }
        if (users !=null && users.size()>0)
            return users;
        else
            return null;
    }
}
