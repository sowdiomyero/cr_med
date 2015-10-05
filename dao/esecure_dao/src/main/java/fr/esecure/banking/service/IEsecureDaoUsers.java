/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * @author sniang
 */
public interface IEsecureDaoUsers extends CrudRepository<User, Long>{

    public boolean isUserWithEmailExist(String emailAdresse);
    public List<User> findAllUsers(); 
    public User findUserById(Long idUser);
    public User findUserByEmail(String userMail);
    public User findUserByIdCompte(Long idCompte);
    public void updateUser(User user );
    public List<User> findAllUsersWithoutUserConnected(String login);



}
