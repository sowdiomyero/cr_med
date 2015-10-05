/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esecure.banking.metier;

import fr.esecure.banking.modele.client.entities.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import org.junit.Assert;


/**
 *
 * @author sniang
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-metier-config.xml"})
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class TestMetier {

    public TestMetier() {
    }

    @Autowired
    IEsecureMetier metier;
    public Long idUserCreated = 0L;

 

    User userTest=null;

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
     userTest=createUser();
    }

    @After
    public void tearDown() {
        if(userTest != null && userTest.getIdUser() != null){
            metier.delete(userTest.getIdUser(), User.class);
        }
    }




    @Test
    public void save_delete_remove() {
        //Création d'un nouveau compte et initialisation des variables
        Compte cpt = new Compte();
        cpt.setDateCreation();
        cpt.setEtatCompte(1);
        cpt.setLogin("loginTest2");
        cpt.setPassword("password2");
        
        //Création d'un compte rendu 
        CompteRendu cr = new CompteRendu(new Date(), "le compte rendu bis", "ok");
        //creation d'un nouveau utilisateur par une initialisation via controlleur
        User newUser = createUser();
        newUser.setCompte(cpt);
       // assertNotNull(metier.saveObject(newUser));  
      //Creéation de la liste des adresses
        List<Adresse> adresses = new ArrayList<Adresse>();
        //Adresse adresse = new Adresse(24, "bayonne", "chemin d'aranchette", 3, 301);
       // Adresse adresse1 = new Adresse(1, "Toulouse", "Impasse andré", 2, 216);
       // adresses.add(adresse);
       // adresses.add(adresse1);

        //Banque banque = new Banque("12", adresse.toString(),null, null);

      //  ParamAcces param = new ParamAcces("localhost", 80, "127.0.0.1", "admin", "admin",banque );

        AccuseReception accuse = new AccuseReception(new Date(), "transaction passée avec succés", "ok");

        CompteRendu compteRendu = new CompteRendu(new Date(), "c'est bon", "ok");

        InfoFormulaire infoFormulaire = new InfoFormulaire("seydou", "niang", "123456", "1245 1234 2530", "douseyi@live.com", "24", "medina centre", "appartement", "le cayor","10000", "nioro");
      

        Client client = new Client("seydou", "oumar", "123456", "douseyi@hotmail.com", adresses);


        CarteBancaire carteBancaire = new CarteBancaire("6452 3214 2314 5623", new Date(), 123, "visa", client);

//        Transaction transaction = new Transaction(2015, 1, new Date(),"EBAY", "www.ebay.com", Transaction.TRANSACTION_STATES.CANCELLED, carteBancaire, adresse);

        //metier.saveObject(transaction);
       // assertNotNull(metier.findAllTransaction());
        
    }

    @Test
    public void create_new_user() {

        User newUser2 = createUser();
        assertNotNull(newUser2);
        idUserCreated=newUser2.getIdUser();

    }

    private User createUser() {
        if(userTest != null)
            return userTest;
        User newUser2 = new User("esecureTest@gmail.com", 1, "Diom Yero", "SOW", "33669932494");
        newUser2.addRole(new Role("ROLE_ADMIN"));
        newUser2.setUserLogged(0);
        newUser2 = (User) metier.createNewUserAccount(newUser2);
        return newUser2;
    }

   // @Test
    public void is_user_with_email_exist() {
        User newUser2 = createUser();
        boolean isOk=metier.isUserWithEmailExist(newUser2.getUserMail());
        System.out.println("Valeur de la methode isUserWithEmailExist "+isOk);
        Compte u = metier.getAccountWithLoginExist(newUser2.getCompte().getLogin());
        System.out.println("Valeur de retour la methode getAccountWithLoginExist "+u.getLogin());
        Assert.assertTrue(isOk);

    }

   // @Test
    public void is_compte_with_login_exist() {
        User newUser2 = createUser();
        Assert.assertTrue(metier.isAccountWithLoginExist(newUser2.getCompte().getLogin()));
    }

}
