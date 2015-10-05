/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package fr.esecure.banking.metier;

import fr.esecure.banking.modele.client.entities.Demande;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author MKKANE
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class DemandeTest {
    
    @Autowired
    IEsecureMetier metier;
    
    Long idDemandeToHandleWith;

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

   
    
   @Test
    public void get_demande_by_id() {
        //Demande demande = metier.findDemandeById(1L);
        Demande demande = metier.findDemandeById(createNewDemande());
        Assert.assertNotNull(demande);
        Assert.assertNotNull(demande.getTransaction().getCarteBancaire());


    }
    
    private Long createNewDemande(){
        if(idDemandeToHandleWith != null)
            return idDemandeToHandleWith;
        
        return 1L;
    }
}

