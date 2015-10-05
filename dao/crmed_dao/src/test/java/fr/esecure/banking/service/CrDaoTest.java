
package fr.esecure.banking.service;

import fr.esecure.banking.modele.client.entities.CompteRendu;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"file:src/test/resources/test-spring-config.xml"})
public class CrDaoTest {

    ApplicationContext ctxt;

    @Autowired
    IEsecureDaoCompteRendu dao;
    @Autowired
    IEsecureDaoAccuseReception accuse;
    @Autowired
    IEsecureDaoAdresse adresse;
    @Autowired
    IEsecureDaoParamAcces param;
    @Autowired
    IEsecureDaoInfoFormulaire info;
    @Autowired
    IEsecureDaoDemande demande;
    

    @Before
    public void setup() {
    
        System.out.println("bonjour");
    }

  @Test
   public void save_new_compte_rendu() {
        CompteRendu cr = new CompteRendu(new Date(), "le compte rendu avec bean id commented","ok");
        cr = dao.save(cr);
        System.out.println("ID CR : " + cr.getIdCompteRendu());
        assertNotNull(cr.getIdCompteRendu());
  }



}

