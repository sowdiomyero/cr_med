/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.metier;

import fr.esecure.banking.metier.utils.PasswordGenerator;
import fr.esecure.banking.modele.client.entities.Compte;
import fr.esecure.banking.modele.client.entities.Role;
import fr.esecure.banking.modele.client.entities.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 *
 * @author SNIANG
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class UserTest {
    
    @Autowired
    IEsecureMetier metier;
    public Long idUserCreated = 0L;

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }


    
    public void delete_user_by_id() {
      User newUser = new User("global@live.fr", 1, "Global", "Ibrahim", "33669932494"); 
      newUser.addRole(new Role("ROLE_USER"));
      newUser= (User) metier.createNewUserAccount(newUser); 
      metier.delete(newUser.getIdUser(), User.class);
      User userToFind = (User) metier.findEntityById(148L, User.class);
    }
    
     @Test
    public void create_user() {
        //Création d'un nouveau compte 
         //creation d'utilisateur 
        Compte cpt = new Compte();
        cpt.setDateCreation();
        cpt.setEtatCompte(1);
       
        String emailUser = "moustaphakamal@gmail.com";
        User newUser = new User(emailUser, 1, "Kane", "Serigne Moustapha", "00221772568262");
        cpt.setLogin(PasswordGenerator.splitEmailForLogin(emailUser));
        cpt.setPassword(PasswordGenerator.crypter("moustaphakamal"));
        newUser.setCompte(cpt);
       // metier.saveObject(newUser);  
        
    }
    
}
