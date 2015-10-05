/*
package fr.esecure.banking.metier;

*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*


import fr.esecure.banking.DocumentType;
import fr.esecure.banking.Periodicite;
import fr.esecure.banking.metier.shedules.ExecuteTask;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

*/
/**
 *
 * @author sniang
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class GeneratorTest {

    @Autowired
    IEsecureMetier metier;
    
    @Autowired
    ExecuteTask task;

    public GeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

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
public void generate_rapport_to_pdf() throws Exception{
    String path= "src"+File.separator+"test"+File.separator+"resources";
    File file = new File(path);
    //path= metier.generateTransactionReport(DocumentType.PDF, Periodicite.MENSUEL,file.getAbsolutePath());
    path= metier.generateTransactionReport(DocumentType.PDF, Periodicite.TOUS_LES_LUNDIS,null);

    System.out.println("Chemin du fichier : "+path);
}

*/
/*@Test
public void launch_shedule_task() throws Exception{
   task.demoServiceMethod();
   Thread.sleep(12000);
}*//*


}
*/
