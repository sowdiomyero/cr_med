/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.esecure.banking.metier;

import fr.esecure.banking.modele.client.entities.Adresse;
import fr.esecure.banking.modele.client.entities.CarteBancaire;
import fr.esecure.banking.modele.client.entities.Client;
import fr.esecure.banking.modele.client.entities.Transaction;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author SNIANG
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class TransactionTest {
    
    @Autowired
    IEsecureMetier metier;
    public Long idUserCreated = 0L;

    @AfterClass
    public static void tearDownClass() {
    }
    private CarteBancaire carteBancaire;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void add_new_transaction_processing() {
        
        List<Adresse> adresses = new ArrayList<Adresse>();
       // Adresse adresse = new Adresse(24, "bayonne", "chemin d'aranchette", 3, 301,"1000");
       // Adresse adresse1 = new Adresse(1, "Toulouse", "Impasse andré", 2, 216,"2000");
//        adresses.add(adresse);
//        adresses.add(adresse1);
//        Client client = new Client("ClientName", "ESECURE METIER", "987654", "esecure@hotmail.com", adresses);
//        CarteBancaire carteBancaire = new CarteBancaire("6452 3214 2314 5623", new Date(), 123, "visa",client );
//        //création d
//   
//        Transaction transaction = new Transaction(2015, 1, new Date(),"EBAY", "www.ebay.com", Transaction.TRANSACTION_STATES.CANCELLED, carteBancaire, adresse);
        //carteBancaire.addTransaction(transaction);
        //metier.saveObject(transaction);
        //metier.saveObject(param);

        //metier.saveObject(demande);
        
        //Test de listing de toutes les transactions
        assertNotNull(metier.findAllTransaction());
         assertNotNull(metier.findAllTransactionNotValidated());
        
    }
    @Test
    public void reject_new_transaction() {

        List<Adresse> adresses = new ArrayList<Adresse>();
//        Adresse adresse = new Adresse(24, "bayonne", "chemin d'aranchette", 3, 301,"3000");
//        Adresse adresse1 = new Adresse(1, "Toulouse", "Impasse andré", 2, 216,"4000");
//        adresses.add(adresse);
//        adresses.add(adresse1);

        CarteBancaire carteBancaire = new CarteBancaire("6452 3214 2314 5623", new Date(), 123, "visa", new Client());
        //création d
       
  
       // Transaction transaction = new Transaction(20000, 1, new Date(),"amazone", "www.amazone.com", Transaction.TRANSACTION_STATES.REJECTED, carteBancaire, adresse);
       // metier.saveObject(transaction);
      

        //metier.saveObject(demande);

        //Test de listing de toutes les transactions
        assertNotNull(metier.findAllTransaction());
        assertNotNull(metier.findAllTransactionNotValidated());

    }
    
   
    public void get_transaction_cartes_list() {
        Transaction tr=(Transaction) metier.findEntityById(1L, Transaction.class);
        Assert.assertNotNull(tr);
        Assert.assertNotNull(tr.getCarteBancaire());
        Assert.assertNotNull(tr.getCarteBancaire().getTransactions());
        Assert.assertEquals(tr.getCarteBancaire().getTransactions().size(), 2);

    }
}
